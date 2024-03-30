package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
