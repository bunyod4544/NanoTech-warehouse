package uz.ab.warehouse.dto.product;

import lombok.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:28
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;

    private Long warehouseId;

    private Integer count;

    private boolean deleted;
}
