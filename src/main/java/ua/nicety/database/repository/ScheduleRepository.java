package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nicety.database.entity.Schedule;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    Optional<Schedule> findById(Long id);
    Optional<Schedule> findAllByUserId(Long id);

    void deleteById(Long id);

//    void update(Long id, Schedule schedule);
}