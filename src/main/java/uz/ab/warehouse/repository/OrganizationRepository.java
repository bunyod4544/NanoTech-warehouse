package uz.ab.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ab.warehouse.entity.Organization;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:29
 */

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {


}
