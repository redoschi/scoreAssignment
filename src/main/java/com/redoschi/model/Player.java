package com.redoschi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

public class Player implements Comparable<Player> {

    public static final String CSV_HEADERS = "Player;Team;Pos;Att;Att/G;Yds;Avg;Yds/G;TD;Lng;1st;1st%;20+;40+;FUM";

    private String name;
    private String team;
    private String pos;
    private Integer att;
    private Double attGame;
    private String yds;
    private Double avg;
    private Double ydsGame;
    private Integer td;
    private String lng;
    private Integer first;
    private Double firstPercentage;
    private Integer twentyPlus;
    private Integer fortyPlus;
    private Integer fum;

    public Player () {
    }

    @JsonCreator
    public Player (
            @JsonProperty("Player") String name,
            @JsonProperty("Team") String team,
            @JsonProperty("Pos") String pos,
            @JsonProperty("Att") Integer att,
            @JsonProperty("Att/G") Double attGame,
            @JsonProperty("Yds") String yds,
            @JsonProperty("Avg") Double avg,
            @JsonProperty("Yds/G") Double ydsGame,
            @JsonProperty("TD") Integer td,
            @JsonProperty("Lng") String lng,
            @JsonProperty("1st") Integer first,
            @JsonProperty("1st%") Double firstPercentage,
            @JsonProperty("20+") Integer twentyPlus,
            @JsonProperty("40+") Integer fortyPlus,
            @JsonProperty("FUM") Integer fum) {
        this.name = name;
        this.team = team;
        this.pos = pos;
        this.att = att;
        this.attGame = attGame;
        this.yds = yds;
        this.avg = avg;
        this.ydsGame = ydsGame;
        this.td = td;
        this.lng = lng;
        this.first = first;
        this.firstPercentage = firstPercentage;
        this.twentyPlus = twentyPlus;
        this.fortyPlus = fortyPlus;
        this.fum = fum;
    }

    @Override
    public int compareTo(Player o) {
        return StringUtils.compare(this.getName(),o.getName());
    }

    @JsonIgnore
    public Integer getCleanLongestRush(){
        String cleanLgn = this.getLng().replace("T","");
        return Integer.valueOf(cleanLgn);
    }

    @JsonIgnore
    public Integer getCleanTotalYards(){
        String cleanYds = this.getYds().replace(",","");
        return Integer.valueOf(cleanYds);
    }

    /**
     * prepare CSV line
     * @return
     */
    public String csvLine() {
        StringBuilder sb = new StringBuilder();
        if(this.name != null) {
            sb.append(this.name);
        }
        sb.append(";");
        if(this.team != null) {
            sb.append(this.team);
        }
        sb.append(";");
        if(this.pos != null) {
            sb.append(this.pos);
        }
        sb.append(";");
        if(this.att != null) {
            sb.append(this.att);
        }
        sb.append(";");
        if(this.attGame != null) {
            sb.append(this.attGame);
        }
        sb.append(";");
        if(this.yds != null) {
            sb.append(this.getCleanTotalYards());
        }
        sb.append(";");
        if(this.avg != null) {
            sb.append(this.avg);
        }
        sb.append(";");
        if(this.ydsGame != null) {
            sb.append(this.ydsGame);
        }
        sb.append(";");
        if(this.td != null) {
            sb.append(this.td);
        }
        sb.append(";");
        if(this.lng != null) {
            sb.append(this.lng);
        }
        sb.append(";");
        if(this.first != null) {
            sb.append(this.first);
        }
        sb.append(";");
        if(this.firstPercentage != null) {
            sb.append(this.firstPercentage);
        }
        sb.append(";");
        if(this.twentyPlus != null) {
            sb.append(this.twentyPlus);
        }
        sb.append(";");
        if(this.fortyPlus != null) {
            sb.append(this.fortyPlus);
        }
        sb.append(";");
        if(this.fum != null) {
            sb.append(this.fum);
        }

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getAtt() {
        return att;
    }

    public void setAtt(Integer att) {
        this.att = att;
    }

    public Double getAttGame() {
        return attGame;
    }

    public void setAttGame(Double attGame) {
        this.attGame = attGame;
    }

    public String getYds() {
        return yds;
    }

    public void setYds(String yds) {
        this.yds = yds;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getYdsGame() {
        return ydsGame;
    }

    public void setYdsGame(Double ydsGame) {
        this.ydsGame = ydsGame;
    }

    public Integer getTd() {
        return td;
    }

    public void setTd(Integer td) {
        this.td = td;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Double getFirstPercentage() {
        return firstPercentage;
    }

    public void setFirstPercentage(Double firstPercentage) {
        this.firstPercentage = firstPercentage;
    }

    public Integer getTwentyPlus() {
        return twentyPlus;
    }

    public void setTwentyPlus(Integer twentyPlus) {
        this.twentyPlus = twentyPlus;
    }

    public Integer getFortyPlus() {
        return fortyPlus;
    }

    public void setFortyPlus(Integer fortyPlus) {
        this.fortyPlus = fortyPlus;
    }

    public Integer getFum() {
        return fum;
    }

    public void setFum(Integer fum) {
        this.fum = fum;
    }
}
