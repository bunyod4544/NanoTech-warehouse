package uz.ab.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ab.warehouse.dto.login.LoginDTO;
import uz.ab.warehouse.dto.user.UserCreateDTO;
import uz.ab.warehouse.dto.user.UserDTO;
import uz.ab.warehouse.service.UserService;

import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:07
 */

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserCreateDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(service.login(dto));
    }

    @GetMapping("/getAllByWarehouseId/{id}")
    public ResponseEntity<List<UserDTO>> getAllByWarehouseId(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.getAllByWarehouseId(id));
    }

    @GetMapping("/getAllByOrganizationId/{id}")
    public ResponseEntity<List<UserDTO>> getAllByOrganizationId(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.getAllByOrganizationId(id));
    }
}
