package bg.tu_varna.sit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.*;
@XmlType(propOrder = {"name", "ranks", "age", "colorSaber", "strength"})
public class Jedi {
    private String name;
    private String rank;
    private int age;
    private String colorSaber;
    private double strength;
    private String[] ranking = {
            "YOUNGLING",
            "INITIATE",
            "PADAWAN",
            "KNIGHT_ASPIRANT",
            "KNIGHT",
            "MASTER",
            "BATTLE_MASTER",
            "GRAND_MASTER"
    };
    private double multiplier = 20.00;

    public Jedi(){}
    public Jedi(String name, String rank, int age, String colorSaber, double strength) {
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.colorSaber = colorSaber;
        this.strength = strength;
    }
@XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getRanks() {
        String ranks = null;
        if(Arrays.asList(ranking).contains(rank))
        {
            ranks = rank;
        }else {
            throw new throwException("You have type wrong rank");
        }
        return ranks;
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

    public void promoteJedi() {
        for(int i=0; i < ranking.length;i++) {
            if (ranking[i] == getRanks()) {
                i++;
                if(i <= ranking.length-1) {
                    this.rank = ranking[i];
                    this.strength += (multiplier * strength);
                }else{
                    throw new throwException("This jedi already achieved the highest rank");
                }
            }
        }
    }
    public void demoteJedi() {
        for(int i=0; i < ranking.length;i++) {
            if (ranking[i] == getRanks()) {
                i--;
                if(i == -1) {
                    throw new throwException("This jedi already achieved the lowest rank");
                }else{
                    this.rank = ranking[i];
                    this.strength -= (multiplier * strength);
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Jedi with name " + name +
                ", rank " + getRanks() +
                ", age " + age +
                ", colorSaber " + colorSaber +
                " and  strength " + strength + "\n";
    }
}
