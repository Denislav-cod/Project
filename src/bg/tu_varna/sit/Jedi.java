package bg.tu_varna.sit;

public class Jedi {
    private String name;
    private Rank rank;
    private int age;
    private String colorSaber;
    private double strength;

    public Jedi(String name, Rank rank, int age, String colorSaber, double strength) {
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

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
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

    @Override
    public String toString() {
        return "Jedi with name " + name +
                ", rank " + rank +
                ", age " + age +
                ", colorSaber " + colorSaber +
                " and  strength " + strength;
    }
}
