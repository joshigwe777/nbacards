package nbacards.models;

import nbacards.Validations.NoDuplicateNbaCard;
import nbacards.Validations.PositionCannotBeInvalid;
import nbacards.Validations.StatsCannotBeLessThanZero;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoDuplicateNbaCard
@StatsCannotBeLessThanZero
@PositionCannotBeInvalid
public class NbaCard {
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    private int cardId;

    @NotBlank(message = "'name' is required.")
    private String name;

    @Min(value=0, message = "points per game cannot be less than zero")
    private Double ppg;

    @Min(value=0, message = "assists per game cannot be less than zero")
    private Double apg;

    @Min(value=0, message = "rebounds per game cannot be less than zero")
    private Double rpg;

    private String position;

    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)",
            message = "'imageUrl' is required.")
    private String imgUrl;

    @Min(value = 1, message = "team is required")
    private int teamId;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public NbaCard() {}
    public NbaCard(int cardId, String name, Double ppg, Double apg, Double rpg, String position, String imgUrl, int teamId) {
        this.cardId = cardId;
        this.name = name;
        this.ppg = ppg;
        this.apg = apg;
        this.rpg = rpg;
        this.position = position;
        this.imgUrl = imgUrl;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPpg() {
        return ppg;
    }

    public void setPpg(Double ppg) {
        this.ppg = ppg;
    }

    public Double getApg() {
        return apg;
    }

    public void setApg(Double apg) {
        this.apg = apg;
    }

    public Double getRpg() {
        return rpg;
    }

    public void setRpg(Double rpg) {
        this.rpg = rpg;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
