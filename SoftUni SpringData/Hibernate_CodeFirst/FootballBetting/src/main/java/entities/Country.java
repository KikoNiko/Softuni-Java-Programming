package entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(length = 3)
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id")
    )
    private Set<Continent> continents;
}
