package ua.nicety.database.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nicety.database.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    @EntityGraph(attributePaths = {"schedules"})
    User getByEmail(String email);

    User getById(Long id);

    Optional<User> findByUsername(String name);

    void deleteById(Long id);
}