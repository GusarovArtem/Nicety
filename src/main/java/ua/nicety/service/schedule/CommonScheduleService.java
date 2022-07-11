package ua.nicety.service.schedule;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.dto.read.ScheduleReadDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonScheduleService implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    @Override
    public Schedule getById(String id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<ScheduleReadDto> findById(String id) {
        return scheduleRepository.findById(id).map(schedule -> modelMapper.map(schedule, ScheduleReadDto.class));
    }

    @Override
    public List<ScheduleReadDto> findAllByAuthor(User user) {
        return scheduleRepository.findAllByAuthor(user)
                .stream().map(schedule -> modelMapper.map(schedule, ScheduleReadDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ScheduleReadDto> create(ScheduleCreateEditDto scheduleDto) {
        return Optional.of(scheduleDto)
                .map(schedule -> modelMapper.map(scheduleDto, Schedule.class))
                .map(scheduleRepository::save)
                .map(schedule -> modelMapper.map(schedule, ScheduleReadDto.class));
    }

    @Transactional
    public Optional<ScheduleReadDto> update(String id, ScheduleCreateEditDto scheduleDto) {
        return scheduleRepository.findById(id)
                .map(entity -> {
                    Schedule mappedEntity = modelMapper.map(scheduleDto, Schedule.class);
                    mappedEntity.setId(entity.getId());
                    return mappedEntity;
                })
                .map(scheduleRepository::saveAndFlush)
                .map(schedule -> modelMapper.map(schedule, ScheduleReadDto.class));
    }

    @Transactional
    public boolean delete(String id) {
        return scheduleRepository.findById(id)
                .map(entity -> {
                    scheduleRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}

