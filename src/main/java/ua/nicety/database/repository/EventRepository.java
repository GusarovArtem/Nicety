package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nicety.database.entity.Event;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    Optional<Event> findById(Long id);

    void deleteById(Long id);

//    void update(Long id, Event event);
}
