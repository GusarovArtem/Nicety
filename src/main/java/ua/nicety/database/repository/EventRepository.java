package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository<E> extends JpaRepository<E, Long>  {

    @Query(nativeQuery = true, value = "SELECT * FROM E where E.schedule_id = :scheduleId")
    List<E> findByScheduleId(String scheduleId);

    @Query(nativeQuery = true, value = "SELECT * FROM E where E.name = :name")
    List<E> findAllByName(String name);
}
