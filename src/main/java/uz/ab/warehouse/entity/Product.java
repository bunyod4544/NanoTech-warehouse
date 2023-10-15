package uz.ab.warehouse.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 17:48
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}
