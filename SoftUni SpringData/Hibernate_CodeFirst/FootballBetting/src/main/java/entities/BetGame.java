package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bet_games")
public class BetGame {
   @Id
   @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Bet bet;

    @OneToOne
    @JoinColumn(name = "result_prediction")
    private ResultPrediction resultPrediction;
}
