package uz.ab.warehouse.dto.user;

import lombok.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:25
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private Long id;

    private String username;

    private String password;

    private Long warehouseId;

}
