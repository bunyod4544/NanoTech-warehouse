package uz.ab.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ab.warehouse.dto.login.LoginDTO;
import uz.ab.warehouse.dto.user.UserCreateDTO;
import uz.ab.warehouse.dto.user.UserDTO;
import uz.ab.warehouse.entity.User;
import uz.ab.warehouse.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:06
 */

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public UserDTO register(UserCreateDTO dto){
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .warehouseId(dto.getWarehouseId())
                .build();

        repository.save(user);
        return mapToDTO(user);
    }

    public UserDTO login (LoginDTO dto){
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            return mapToDTO(user);
        }catch (Exception e){
            throw new RuntimeException("Bad credentials!");
        }
    }

    public List<UserDTO> getAllByWarehouseId(Long warehouseId){
        List<User> users = repository.findAllByWarehouseId(warehouseId).stream()
                .filter(user -> !user.isDeleted()).toList(); //filtering active users.
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(mapToDTO(user));
        }
        return userDTOS;
    }

    public List<UserDTO> getAllByOrganizationId(Long organizationId){
        List<User> users = repository.findAllByOrganizationId(organizationId).stream()
                .filter(user -> !user.isDeleted()).toList(); //filtering active users.
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(mapToDTO(user));
        }
        return userDTOS;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
    }

    public UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .warehouseId(user.getWarehouseId())
                .role(user.getRole().toString())
                .isDeleted(user.isDeleted())
                .build();
    }
}
