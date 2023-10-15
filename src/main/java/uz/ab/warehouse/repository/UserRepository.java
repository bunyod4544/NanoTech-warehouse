package uz.ab.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ab.warehouse.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:07
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByWarehouseId(Long id);

    @Query(value = "select * from users u " +
            "inner join warehouse w ON u.warehouse_id=w.id " +
            "inner join organization o ON w.organization_id=o.id " +
            "where o.id=:organizationId",nativeQuery = true)
    List<User> findAllByOrganizationId(@Param("organizationId") Long organizationId);

}
