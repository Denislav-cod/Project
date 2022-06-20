package bg.tu_varna.sit;

import bg.tu_varna.sit.Exceptions.PlanetException;

import java.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Planet {
    private String name;
    private List<Jedi> jedis;
    public Planet(String name) {
        this.name = name;
        this.jedis = new ArrayList<>();
    }
    public Planet(){}

    public void addJedis(Jedi jedi){
        this.jedis.add(jedi);
        this.jedis.sort(Comparator.comparing(Jedi::getRank).thenComparing(Jedi::getName));
    }

    public List<Jedi> getJedis() {
        return jedis;
    }

    public void removeJedi(String jName){
        for(Jedi jedi : this.getJedis()){
            if(jedi.getName().equals(jName)){
                if (this.getJedis().contains(jedi)){
                    jedis.removeIf(j -> j.getName().equals(jName));
                }
            }else{
                throw new PlanetException("This jedi not exist or its deleted");
            }
        }
    }

    public void promoteJedi(String jName, double multiplier){
        for(Jedi j : jedis){
            if(j.getName().equals(jName)){
                j.promoteJedi(jName,multiplier);
            }
        }
    }
    public void demoteJedi(String jName, double multiplier){
        for(Jedi j : jedis){
            if(j.getName().equals(jName)){
                j.demoteJedi(jName,multiplier);
            }
        }
    }
    public void getStrongestJedi() {
        String j = null;
        double cnt = 0;
        for (Jedi jedi : jedis) {
            if (cnt == 0) {
                cnt = jedi.getStrength();
                j = jedi.toString();
            } else if (jedi.getStrength() > cnt) {
                cnt = jedi.getStrength();
            }
        }
        System.out.println(j);
    }

    public void getYoungestJedi(String rank) {
        List<Jedi> str = new ArrayList<>();
        for (Jedi jedi : jedis) {
            if (jedi.getRank().equals(rank)) {
                if(jedi.getAge() == jedi.getAge()){
                    str.add(jedi);
                    str.sort(Comparator.comparing(Jedi::getName));
                }
            }
        }
        for(Jedi j : str)
            System.out.println(j.toString());
    }
    public void getMostUsedSaberColor(){
        List<String> colors = new ArrayList<>();
        String mostCommon = null;
        int cnt = 0;
        for (Jedi jedi : jedis) {
            colors.add(jedi.getColorSaber());
            if (jedi.getRank().equals("GRAND_MASTER")) {
                if (cnt == 0) {
                    cnt++;
                    mostCommon = jedi.getColorSaber();
                } else if (colors.size() > cnt) {
                    cnt++;
                    mostCommon = jedi.getColorSaber();
                }
            }
        }
        if(mostCommon == null){
            mostCommon = "There isn't most used color from GRAND_MASTER";
        }
        System.out.println(mostCommon);
    }
    public void getMostUsedSaberColor(String rank){
        List<String> colors = new ArrayList<>();
        String mostCommon = null;
        int cnt = 0;
        for (Jedi jedi : jedis) {
            colors.add(jedi.getColorSaber());
            if (jedi.getRank().equals(rank)) {
                if (cnt == 0) {
                    cnt++;
                    mostCommon = jedi.getColorSaber();
                } else if (colors.size() > cnt) {
                    cnt++;
                    mostCommon = jedi.getColorSaber();
                }
            }
        }
        if(mostCommon == null){
            mostCommon = "There isn't most used color from" + rank;
        }
        System.out.println(mostCommon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
       /* return "Planet{" +
                "name='" + name + '\'' +
                '}';
        */
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(System.lineSeparator());
        for (Jedi jedi : jedis) {
            sb.append(jedi.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
