package lottery.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer draws;

    @NotEmpty
    @Transient
    private String player;

    @NotNull
    @Min(1)
    @Max(49)
    private Integer firstNumber;

    @NotNull
    @Min(1)
    @Max(49)
    private Integer secondNumber;

    @NotNull
    @Min(1)
    @Max(49)
    private Integer thirdNumber;

    @NotNull
    @Min(1)
    @Max(49)
    private Integer fourthNumber;

    @NotNull
    @Min(1)
    @Max(49)
    private Integer fifthNumber;

    @NotNull
    @Min(1)
    @Max(49)
    private Integer sixthNumber;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Integer getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(Integer thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Integer getFourthNumber() {
        return fourthNumber;
    }

    public void setFourthNumber(Integer fourthNumber) {
        this.fourthNumber = fourthNumber;
    }

    public Integer getFifthNumber() {
        return fifthNumber;
    }

    public void setFifthNumber(Integer fifthNumber) {
        this.fifthNumber = fifthNumber;
    }

    public Integer getSixthNumber() {
        return sixthNumber;
    }

    public void setSixthNumber(Integer sixthNumber) {
        this.sixthNumber = sixthNumber;
    }

}

