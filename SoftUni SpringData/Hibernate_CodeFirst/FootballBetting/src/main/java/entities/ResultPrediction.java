package entities;

import entities.enums.ResultPredictionsValues;
import jakarta.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity {
    @Column(name = "prediction")
    @Enumerated(EnumType.STRING)
    private ResultPredictionsValues resultPrediction;
}
