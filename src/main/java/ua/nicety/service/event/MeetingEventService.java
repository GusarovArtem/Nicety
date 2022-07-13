package ua.nicety.service.event;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.event.Meeting;
import ua.nicety.database.repository.MeetingRepository;
import ua.nicety.http.dto.EventCreateEditDto;
import ua.nicety.http.dto.read.MeetingReadDto;
import ua.nicety.service.mail.MailService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service("meeting")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingEventService implements EventService<Meeting, MeetingReadDto> {

    private final MeetingRepository repository;
    private final MailService mailService;
    private final ModelMapper modelMapper;

    public Meeting create(EventCreateEditDto eventDto) {
        return Optional.of(eventDto)
                .map(createDto -> modelMapper.map(createDto, Meeting.class))
                .map(repository::save).orElse(null);
    }

    @Transactional
    public Optional<Meeting> update(Long id, EventCreateEditDto eventDto) {
        return repository.findById(id)
                .map(entity -> {
                    Meeting mappedEntity = modelMapper.map(eventDto, Meeting.class);
                    mappedEntity.setId(entity.getId());
                    return mappedEntity;
                })
                .map(repository::saveAndFlush);
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<Meeting> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MeetingReadDto> findByScheduleId(String id) {
        return repository.findByScheduleId(id)
                .stream().map(event -> modelMapper.map(event, MeetingReadDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Map<LocalDateTime, List<MeetingReadDto>> findAllByName(String name, String scheduleId) {
        List<MeetingReadDto> events = findByScheduleId(scheduleId);
        return events.stream()
                .filter(eventReadDto -> eventReadDto.getName().equals(name))
                .sorted()
                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
    }

    @Override
    public Map<LocalDateTime, List<MeetingReadDto>> getMapEvents(String scheduleId) {
        List<MeetingReadDto> events = findByScheduleId(scheduleId);

        return events.stream()
                .sorted()
                .collect(Collectors.groupingBy(LocalDateTime::from, TreeMap::new, Collectors.toList()));
    }

    @Scheduled(cron = "0 * * * * *")
    public void notifyUsers() {
        List<Meeting> meetings = repository.findByDateTimeEqualsAndNotifyIsTrue(LocalDateTime.now().plusMinutes(5));

        meetings.forEach((meeting) -> {
             String email = meeting.getSchedule().getAuthor().getEmail();
             mailService.sendMeeting(meeting, email);
        });

    }


}