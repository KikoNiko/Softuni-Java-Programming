package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "wizards")
public class Wizard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 1000, columnDefinition = "TEXT")
    private String notes;

    @OneToOne
    @JoinColumn(name = "magic_wand_id")
    private MagicWand magicWand;

    @OneToMany
    @JoinTable(name = "wizard_deposits")
    private List<Deposit> deposits;

    public Wizard() {
    }


}
