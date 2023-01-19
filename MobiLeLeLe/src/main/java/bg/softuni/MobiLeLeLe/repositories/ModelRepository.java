package bg.softuni.MobiLeLeLe.repositories;

import bg.softuni.MobiLeLeLe.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, String> {
}
