package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class Bet extends BaseEntity {
   @Column(name = "bet_money")
    private BigDecimal money;

    @Column(name = "time_of_bet")
    private Date timeOfBet;

    @ManyToOne
    private User user;
}
