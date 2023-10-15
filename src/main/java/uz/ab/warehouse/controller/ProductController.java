package uz.ab.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ab.warehouse.dto.product.ProductCreateDTO;
import uz.ab.warehouse.dto.product.ProductDTO;
import uz.ab.warehouse.dto.product.ProductSellDTO;
import uz.ab.warehouse.dto.product.ProductUpdateDTO;
import uz.ab.warehouse.service.ProductService;

import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:36
 */

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductUpdateDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/sell")
    public ResponseEntity<ProductDTO> sell(@RequestBody ProductSellDTO dto){
        return ResponseEntity.ok(service.sell(dto));
    }

    @GetMapping("/getAllByWarehouseId/{id}")
    public ResponseEntity<List<ProductDTO>> getAllByWarehouseId(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.getAllByWarehouseId(id));
    }

    @GetMapping("/getAllByOrganizationId/{id}")
    public ResponseEntity<List<ProductDTO>> getAllByOrganizationId(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.getAllByOrganizationId(id));
    }
}
