package uz.ab.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ab.warehouse.dto.organization.OrganizationCreateDTO;
import uz.ab.warehouse.dto.organization.OrganizationDTO;
import uz.ab.warehouse.dto.organization.OrganizationUpdateDTO;
import uz.ab.warehouse.service.OrganizationService;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:36
 */

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationCreateDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationUpdateDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.get(id));
    }

}
