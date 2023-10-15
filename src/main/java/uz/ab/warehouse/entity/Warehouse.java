package uz.ab.warehouse.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 17:40
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "organization_id", nullable = false, unique = true)
    private Long organizationId;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}
