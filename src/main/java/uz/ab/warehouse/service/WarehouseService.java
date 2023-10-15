package uz.ab.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ab.warehouse.dto.warehouse.WarehouseCreateDTO;
import uz.ab.warehouse.dto.warehouse.WarehouseDTO;
import uz.ab.warehouse.dto.warehouse.WarehouseUpdateDTO;
import uz.ab.warehouse.entity.Product;
import uz.ab.warehouse.entity.User;
import uz.ab.warehouse.entity.Warehouse;
import uz.ab.warehouse.repository.ProductRepository;
import uz.ab.warehouse.repository.UserRepository;
import uz.ab.warehouse.repository.WarehouseRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:35
 */
@Service
public class WarehouseService {

    private final WarehouseRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WarehouseService(WarehouseRepository repository, UserRepository userRepository, ProductRepository productRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public WarehouseDTO create (WarehouseCreateDTO dto){
        Warehouse warehouse = Warehouse.builder()
                .organizationId(dto.getOrganizationId())
                .name(dto.getName())
                .deleted(dto.isDeleted())
                .build();
        repository.save(warehouse);

        return mapToDTO(warehouse);
    }

    public WarehouseDTO update (WarehouseUpdateDTO dto){
        Warehouse warehouse = findById(dto.getId());

        warehouse.setName(dto.getName());
        Warehouse saved = repository.save(warehouse);
        return mapToDTO(warehouse);
    }

    @Transactional
    public String delete(Long id){
        Warehouse warehouse = findById(id);
        warehouse.setDeleted(true);
        repository.save(warehouse);
        for (User user : userRepository.findAllByWarehouseId(id)) {
            user.setDeleted(true);
            userRepository.save(user);
        }
        for (Product product : productRepository.findAllByWarehouseId(id)) {
            product.setDeleted(true);
            productRepository.save(product);
        }
        return "success";
    }

    public WarehouseDTO get(Long id){
        Warehouse warehouse = findById(id);

        return mapToDTO(warehouse);
    }

    public List<WarehouseDTO> getAll(){
        List<Warehouse> warehouses = repository.findAll().stream()
                .filter(warehouse -> !warehouse.isDeleted()).toList(); //filtering all active warehouses;
        return getWarehouseDTOS(warehouses);
    }

    public List<WarehouseDTO> getAllByOrganizationId(Long organizationId){
        List<Warehouse> warehouses = repository.findAllByOrganizationId(organizationId).stream()
                .filter(warehouse -> !warehouse.isDeleted()).toList();//filtering all active warehouses;
        return getWarehouseDTOS(warehouses);
    }

    private List<WarehouseDTO> getWarehouseDTOS(List<Warehouse> warehouses) {
        List<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            WarehouseDTO warehouseDTO = mapToDTO(warehouse);
            warehouseDTOS.add(warehouseDTO);
        }
        return warehouseDTOS;
    }

    private Warehouse findById(Long id){
        Warehouse warehouse = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found!"));
        if(warehouse.isDeleted())
            throw new RuntimeException("Warehouse not found!");
        return warehouse;
    }

    private WarehouseDTO mapToDTO(Warehouse warehouse){
        return WarehouseDTO.builder()
                .name(warehouse.getName())
                .organizationId(warehouse.getOrganizationId())
                .isDeleted(warehouse.isDeleted())
                .build();
    }
}
