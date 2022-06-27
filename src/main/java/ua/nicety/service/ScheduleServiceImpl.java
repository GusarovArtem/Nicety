package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.mapper.ScheduleCreateEditMapper;
import ua.nicety.service.interfaces.ScheduleService;

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

    @Transactional
    public Optional<Schedule> create(ScheduleCreateEditDto scheduleDto) {
        return Optional.of(scheduleDto)
                .map(scheduleCreateEditMapper::map)
                .map(scheduleRepository::save);
    }

    @Transactional
    public boolean update(String id, ScheduleCreateEditDto scheduleDto) {
        return scheduleRepository.findById(id)
                .map(entity -> scheduleCreateEditMapper.map(scheduleDto, entity))
                .map(schedule -> {
                    scheduleRepository.saveAndFlush(schedule);
                    return true;
                }).orElse(false);
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

