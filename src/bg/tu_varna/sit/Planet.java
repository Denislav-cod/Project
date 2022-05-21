package bg.tu_varna.sit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class Planet {
    private String name;
    @XmlElement(name = "jedi")
    private List<Jedi> jedis;

    public Planet(){
    }

    public Planet(String name) {
        this.name = name;
        this.jedis = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addJediToPlanet(Jedi jedi) {
             if (jedis.contains(jedi)) {
            throw new throwException("This jedi already exists on this planet");
        } else {
            this.jedis.add(jedi);
        }
    }

    public void removeJedi(Jedi jedi) {
        if(!this.jedis.contains(jedi)){
            throw new throwException("This jedi doesn't exist on this planet or he is already deleted");
        }else{
            this.jedis.remove(jedi);
            System.out.println("Successfully Deleted Jedi");
        }

    }
// Need to be improved check the documentation
    public void getMostUsedColorSaber() {
      List<String> colors = new ArrayList<>();
      String mostCommon = null;
      int cnt = 0;
      for(Jedi jedi:jedis){
          colors.add(jedi.getColorSaber());
          for (String color: colors){
                if(cnt == 0){
                    cnt++;
                    mostCommon = jedi.getColorSaber();
                }else if (colors.size() > cnt){
                    cnt ++;
                    mostCommon = jedi.getColorSaber();
                }
          }
      }
      System.out.println(mostCommon);
    }

    public void getStrongestJedi(){
        String name = null;
        double cnt = 0;
        for(Jedi jedi : jedis){
            if(cnt == 0){
                cnt = jedi.getStrength();
                name = jedi.getName();
            }else if(jedi.getStrength() > cnt){
                cnt = jedi.getStrength();
                name = jedi.getName();
            }
        }
        System.out.println(name + " " +cnt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName() + System.lineSeparator());
        for (Jedi jedi : jedis) {
            sb.append(jedi.toString());
        }
        return sb.toString().trim();
    }

}
