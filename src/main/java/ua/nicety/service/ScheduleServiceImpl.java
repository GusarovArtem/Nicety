package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.mapper.ScheduleCreateEditMapper;
import ua.nicety.service.interfaces.ScheduleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleCreateEditMapper scheduleCreateEditMapper;

    public void create(ScheduleCreateEditDto scheduleDto) {
        Optional.of(scheduleDto)
                .map(scheduleCreateEditMapper::map)
                .map(schedule -> {
                    scheduleRepository.save(schedule);
                    return schedule;
                });
    }

    @Transactional
    public boolean update(Long id, ScheduleCreateEditDto scheduleDto) {
        return scheduleRepository.findById(id)
                .map(entity -> scheduleCreateEditMapper.map(scheduleDto, entity))
                .map(schedule -> {
                    scheduleRepository.saveAndFlush(schedule);
                    return true;
                }).orElse(false);
    }

    @Transactional
    public boolean delete(Long id) {
        return scheduleRepository.findById(id)
                .map(entity -> {
                    scheduleRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}

