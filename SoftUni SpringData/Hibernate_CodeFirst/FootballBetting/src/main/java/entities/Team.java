package entities;

import entities.enums.TeamNameInitials;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @Column
    @Enumerated(EnumType.STRING)
    private TeamNameInitials initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color")
    private Color secondaryKitColor;

    @ManyToOne
    private Town town;

    @Column
    private BigDecimal budget;


}
