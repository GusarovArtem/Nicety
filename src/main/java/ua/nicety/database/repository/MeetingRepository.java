package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nicety.database.entity.event.Meeting;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>  {

    @Query(nativeQuery = true, value = "SELECT * FROM meeting m where m.schedule_id = :scheduleId")
    List<Meeting> findByScheduleId(String scheduleId);

    @Query(nativeQuery = true, value = "SELECT * FROM meeting m where m.name = :name")
    List<Meeting> findAllByName(String name);


    List<Meeting> findByDateTimeEqualsAndNotifyIsTrue(LocalDateTime dateTime);
    
}
