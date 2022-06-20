package bg.tu_varna.sit;

import bg.tu_varna.sit.Exceptions.RankException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.*;
@XmlType(propOrder = {"planet","name", "rank", "age", "colorSaber", "strength"})
public class Jedi {
    private String planet;
    private String name;
    private String rank;
    private int age;
    private String colorSaber;
    private double strength;
    private  String[] ranking = {
            "YOUNGLING",
            "INITIATE",
            "PADAWAN",
            "KNIGHT_ASPIRANT",
            "KNIGHT",
            "MASTER",
            "BATTLE_MASTER",
            "GRAND_MASTER"
    };
    public Jedi(){}
    public Jedi(String planet,String name, String rank, int age, String colorSaber, double strength) {
        this.planet = planet;
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.colorSaber = colorSaber;
        this.strength = strength;
    }
    @XmlElement
    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "rank")
    public String getRank() {
        String ranks;
        if (Arrays.asList(ranking).contains(rank)) {
            ranks = this.rank;
            this.rank = ranks;
        } else {
            throw new RankException("You have type wrong rank");
        }
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    @XmlElement
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @XmlElement
    public String getColorSaber() {
        return colorSaber;
    }

    public void setColorSaber(String colorSaber) {
        this.colorSaber = colorSaber;
    }
    @XmlElement
    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void promoteJedi(String jName, double multiplier){
        for (int i = 0; i < ranking.length; i++) {
            if (ranking[i].equals(getRank())) {
                i++;
                if (i <= ranking.length - 1) {
                    this.rank = ranking[i];
                    this.strength += (multiplier * this.strength);
                } else {
                    throw new RankException("This jedi already achieved the highest rank");
                }
            }
        }
    }
    public void demoteJedi(String jName, double multiplier){
        for (int i = 0; i < ranking.length; i++) {
            if (ranking[i].equals(getRank())) {
                i--;
                if (i == -1) {
                    throw new RankException("This jedi already achieved the lowest rank");
                } else {
                    this.rank = ranking[i];
                    this.strength -= (multiplier * this.strength);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Jedi{" +
                "name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", age=" + age +
                ", colorSaber='" + colorSaber + '\'' +
                ", strength=" + strength +
                ", planet=" + planet +
                '}';
    }
}