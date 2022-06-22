package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nicety.database.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
