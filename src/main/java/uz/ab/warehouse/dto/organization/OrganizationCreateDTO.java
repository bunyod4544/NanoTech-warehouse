package uz.ab.warehouse.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Bunyod on 12 октябрь 2023 at 18:19
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreateDTO {

    @NotBlank(message = "Organization name is empty.")
    private String name;

}
