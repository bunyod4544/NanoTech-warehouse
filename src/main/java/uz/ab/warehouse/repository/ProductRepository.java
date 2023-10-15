package uz.ab.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ab.warehouse.entity.Product;

import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:30
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByWarehouseId(Long warehouseId);

    @Query(value = "select * from product p " +
            "inner join warehouse w ON p.warehouse_id=w.id " +
            "inner join organization o ON w.organization_id=o.id " +
            "where o.id=:organizationId",nativeQuery = true)
    List<Product> findAllByOrganizationId(@Param("organizationId") Long organizationId);


}
