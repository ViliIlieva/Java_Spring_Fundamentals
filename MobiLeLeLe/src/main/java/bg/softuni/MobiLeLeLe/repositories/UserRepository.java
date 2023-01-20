package bg.softuni.MobiLeLeLe.repositories;

import bg.softuni.MobiLeLeLe.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
