package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {

    @Column(nullable = false)
    private String name;
}
