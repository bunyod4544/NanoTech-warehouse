package uz.ab.warehouse.dto.user;

import lombok.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:19
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    private String username;

    private String password;

    private Long warehouseId;

}
