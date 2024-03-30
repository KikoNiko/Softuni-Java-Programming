package entities;

import jakarta.persistence.*;

import javax.xml.namespace.QName;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type")
    private CompetitionType competitionType;
}
