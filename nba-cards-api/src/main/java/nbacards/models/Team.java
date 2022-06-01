package nbacards.models;

public class Team {

    private int teamId;
    private String teamName;
    private String cityName;

    public Team(int teamId, String teamName, String cityName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.cityName = cityName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
