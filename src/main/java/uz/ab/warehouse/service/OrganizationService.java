package uz.ab.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ab.warehouse.dto.organization.OrganizationCreateDTO;
import uz.ab.warehouse.dto.organization.OrganizationDTO;
import uz.ab.warehouse.dto.organization.OrganizationUpdateDTO;
import uz.ab.warehouse.entity.Organization;
import uz.ab.warehouse.entity.Product;
import uz.ab.warehouse.entity.User;
import uz.ab.warehouse.entity.Warehouse;
import uz.ab.warehouse.repository.OrganizationRepository;
import uz.ab.warehouse.repository.ProductRepository;
import uz.ab.warehouse.repository.UserRepository;
import uz.ab.warehouse.repository.WarehouseRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:36
 */
@Service
public class OrganizationService {

    private final OrganizationRepository repository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Autowired
    public OrganizationService(OrganizationRepository repository, WarehouseRepository warehouseRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.repository = repository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public OrganizationDTO create(OrganizationCreateDTO dto) {
        Organization organization = Organization.builder()
                .name(dto.getName())
                .build();
        Organization saved = repository.save(organization);
        return OrganizationDTO.builder()
                .name(saved.getName())
                .build();
    }

    public OrganizationDTO update(OrganizationUpdateDTO dto) {
        Organization organization = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Organization not found!"));
        if (organization.isDeleted())
            throw new RuntimeException("Organization not found!");
        organization.setName(dto.getName());
        Organization saved = repository.save(organization);
        return OrganizationDTO.builder()
                .name(saved.getName())
                .build();
    }

    @Transactional
    public String delete(Long id) {
        Organization organization = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found!"));
        if (organization.isDeleted())
            throw new RuntimeException("Organization not found!");
        organization.setDeleted(true);
        repository.save(organization);
        for (Warehouse warehouse : warehouseRepository.findAllByOrganizationId(organization.getId())) {
            warehouse.setDeleted(true);
            warehouseRepository.save(warehouse);
        }
        for (User user : userRepository.findAllByOrganizationId(organization.getId())) {
            user.setDeleted(true);
            userRepository.save(user);
        }
        for (Product product : productRepository.findAllByOrganizationId(organization.getId())) {
            product.setDeleted(true);
            productRepository.save(product);
        }

        return "success";
    }

    public OrganizationDTO get(Long id) {
        Organization organization = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found!"));
        if (organization.isDeleted())
            throw new RuntimeException("Organization not found!");

        return OrganizationDTO.builder().
                name(organization.getName())
                .build();
    }

    public List<OrganizationDTO> getAll() {
        return null;
    }


}
