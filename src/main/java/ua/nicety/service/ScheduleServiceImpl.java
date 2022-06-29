package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.mapper.ScheduleCreateEditMapper;
import ua.nicety.service.interfaces.ScheduleService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleCreateEditMapper scheduleCreateEditMapper;

    @Override
    public Schedule getById(String id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Schedule> findById(String id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findAllByAuthor(User user) {
        return scheduleRepository.findAllByAuthor(user);
    }

    @Transactional
    public Optional<Schedule> create(ScheduleCreateEditDto scheduleDto) {
        return Optional.of(scheduleDto)
                .map(scheduleCreateEditMapper::map)
                .map(scheduleRepository::save);
    }

    @Transactional
    public Optional<Schedule> update(String id, ScheduleCreateEditDto scheduleDto) {
        return scheduleRepository.findById(id)
                .map(entity -> scheduleCreateEditMapper.map(scheduleDto, entity))
                .map(scheduleRepository::saveAndFlush);
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

