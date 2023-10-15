package uz.ab.warehouse.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Bunyod on 12 октябрь 2023 at 17:38
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}
