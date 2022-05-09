package bg.tu_varna.sit;

import java.util.Arrays;

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


    public Jedi(String name, String rank, int age, String colorSaber, double strength) {
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.colorSaber = colorSaber;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRanks(){
        String ranks = null;

            if(Arrays.stream(ranking).anyMatch(rank::equals)){
                ranks = rank;
            }else{
                throw new IncorectValue("Wrong rank or empty value");
            }

        return ranks;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColorSaber() {
        return colorSaber;
    }

    public void setColorSaber(String colorSaber) {
        this.colorSaber = colorSaber;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void promoteJedi(){

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
