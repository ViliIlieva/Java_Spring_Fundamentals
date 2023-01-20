package bg.softuni.MobiLeLeLe.repositories;

import bg.softuni.MobiLeLeLe.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
}
