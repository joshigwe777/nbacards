package nbacards.models;

public class NbaCard {
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    private int cardId;
    private String name;
    private Double ppg;
    private Double apg;
    private Double rpg;
    private String position;
    private String imgUrl;
    private int teamId;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

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
