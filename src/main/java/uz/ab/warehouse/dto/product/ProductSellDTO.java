package uz.ab.warehouse.dto.product;

import lombok.*;

/**
 * @author Bunyod on 15 октябрь 2023 at 12:35
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSellDTO {

    private Long id;

    private Integer count;
}
