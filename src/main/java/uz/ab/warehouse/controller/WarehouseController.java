package uz.ab.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ab.warehouse.dto.warehouse.WarehouseCreateDTO;
import uz.ab.warehouse.dto.warehouse.WarehouseDTO;
import uz.ab.warehouse.dto.warehouse.WarehouseUpdateDTO;
import uz.ab.warehouse.service.WarehouseService;

import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:36
 */

@RestController
@RequestMapping("api/warehouse")
public class WarehouseController {

    private final WarehouseService service;

    @Autowired
    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> create(@RequestBody WarehouseCreateDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<WarehouseDTO> update(@RequestBody WarehouseUpdateDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WarehouseDTO> get(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WarehouseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/getAllByOrganizationId/{id}")
    public ResponseEntity<List<WarehouseDTO>> getAllByOrganizationId(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.getAllByOrganizationId(id));
    }
}
