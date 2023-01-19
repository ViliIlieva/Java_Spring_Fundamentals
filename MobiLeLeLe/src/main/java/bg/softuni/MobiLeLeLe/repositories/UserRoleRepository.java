package bg.softuni.MobiLeLeLe.repositories;

import bg.softuni.MobiLeLeLe.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
}
