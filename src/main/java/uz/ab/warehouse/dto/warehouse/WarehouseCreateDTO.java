package uz.ab.warehouse.dto.warehouse;

import lombok.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:25
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseCreateDTO {
    private String name;

    private Long organizationId;

    private boolean isDeleted;
}
