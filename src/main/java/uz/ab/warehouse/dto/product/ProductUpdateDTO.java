package uz.ab.warehouse.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:29
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {

    @NotBlank(message = "id is empty.")
    private Long id;

    private String name;

    private Long warehouseId;

    private Integer count;
}
