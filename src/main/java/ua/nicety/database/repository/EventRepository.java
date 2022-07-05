package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nicety.database.entity.Event;
import ua.nicety.database.entity.Schedule;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM event e where e.schedule_id = :scheduleId")
    List<Event> findByScheduleId(String scheduleId);

    @Query(nativeQuery = true, value = "SELECT * FROM event e where e.name = :name")
    List<Event> findAllByName(String name);
}
