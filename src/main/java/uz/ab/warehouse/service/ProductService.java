package uz.ab.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ab.warehouse.dto.product.ProductCreateDTO;
import uz.ab.warehouse.dto.product.ProductDTO;
import uz.ab.warehouse.dto.product.ProductSellDTO;
import uz.ab.warehouse.dto.product.ProductUpdateDTO;
import uz.ab.warehouse.entity.Product;
import uz.ab.warehouse.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:35
 */

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO create(ProductCreateDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .warehouseId(dto.getWarehouseId())
                .count(dto.getCount())
                .build();
        repository.save(product);
        return mapToDto(product);
    }

    public ProductDTO update(ProductUpdateDTO dto) {
        Product product = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        if (Objects.nonNull(dto.getName())) {
            product.setName(dto.getName());
        }
        if (Objects.nonNull(dto.getWarehouseId())) {
            product.setWarehouseId(dto.getWarehouseId());
        }
        if (Objects.nonNull(dto.getCount())) {
            product.setCount(dto.getCount());
        }
        repository.save(product);
        return mapToDto(product);
    }

    public ProductDTO sell(ProductSellDTO dto) {
        Product product = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        if (dto.getCount() > product.getCount()) {
            throw new RuntimeException("Wrong count!");
        }
        product.setCount(product.getCount() - dto.getCount());
        repository.save(product);
        return mapToDto(product);
    }

    @Transactional
    public String delete(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(true);
        repository.save(product);
        return "success";
    }

    public List<ProductDTO> getAllByWarehouseId(Long warehouseId){
        List<Product> allByWarehouseId = repository.findAllByWarehouseId(warehouseId)
                .stream().filter(product -> !product.isDeleted()).toList(); //filtering active products
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : allByWarehouseId) {
            productDTOS.add(mapToDto(product));
        }
        return productDTOS;
    }

    public List<ProductDTO> getAllByOrganizationId(Long organizationId){
        List<Product> allByWarehouseId = repository.findAllByOrganizationId(organizationId)
                .stream().filter(product -> !product.isDeleted()).toList(); //filtering active products
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : allByWarehouseId) {
            productDTOS.add(mapToDto(product));
        }
        return productDTOS;
    }

    private ProductDTO mapToDto(Product product){
        return ProductDTO.builder()
                .count(product.getCount())
                .name(product.getName())
                .warehouseId(product.getWarehouseId())
                .deleted(product.isDeleted())
                .build();
    }
}
