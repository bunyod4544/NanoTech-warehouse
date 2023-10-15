package uz.ab.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ab.warehouse.entity.Warehouse;

import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:30
 */

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAllByOrganizationId(Long id);
}
