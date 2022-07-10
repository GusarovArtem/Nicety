package ua.nicety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nicety.database.entity.Schedule;
import ua.nicety.database.entity.User;
import ua.nicety.database.repository.ScheduleRepository;
import ua.nicety.http.dto.ScheduleCreateEditDto;
import ua.nicety.http.dto.read.ScheduleReadDto;
import ua.nicety.http.mapper.ScheduleCreateEditMapper;
import ua.nicety.http.mapper.read.ScheduleReadMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonScheduleService implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleCreateEditMapper scheduleCreateEditMapper;
    private final ScheduleReadMapper scheduleReadMapper;

    @Override
    public Schedule getById(String id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<ScheduleReadDto> findById(String id) {
        return scheduleRepository.findById(id).map(scheduleReadMapper::map);
    }

    @Override
    public List<ScheduleReadDto> findAllByAuthor(User user) {
        return scheduleRepository.findAllByAuthor(user)
                .stream().map(scheduleReadMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ScheduleReadDto> create(ScheduleCreateEditDto scheduleDto) {
        return Optional.of(scheduleDto)
                .map(scheduleCreateEditMapper::map)
                .map(scheduleRepository::save)
                .map(scheduleReadMapper::map);
    }

    @Transactional
    public Optional<ScheduleReadDto> update(String id, ScheduleCreateEditDto scheduleDto) {
        return scheduleRepository.findById(id)
                .map(entity -> scheduleCreateEditMapper.map(scheduleDto, entity))
                .map(scheduleRepository::saveAndFlush)
                .map(scheduleReadMapper::map);
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

