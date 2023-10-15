package uz.ab.warehouse.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:28
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {

    @NotBlank(message = "Product name is empty.")
    private String name;

    private Long warehouseId;

    private Integer count;

}
