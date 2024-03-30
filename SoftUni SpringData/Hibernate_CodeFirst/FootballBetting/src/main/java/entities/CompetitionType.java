package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "competition_type")
public class CompetitionType extends BaseEntity {

    @Column(nullable = false)
    private String name;
}
