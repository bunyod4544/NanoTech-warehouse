package uz.ab.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.ab.warehouse.entity.User;
import uz.ab.warehouse.enums.UserRole;
import uz.ab.warehouse.repository.UserRepository;

@SpringBootApplication
public class WarehouseApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .username("Bunyod")
                .password(passwordEncoder.encode("bunyod"))
                .warehouseId(-1L)
                .role(UserRole.ADMIN)
                .build();
        userRepository.save(user);
    }
}
