package bg.tu_varna.sit;

import bg.tu_varna.sit.Exceptions.PlanetException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

@XmlRootElement(name = "planet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Universe {
    @XmlElement(name = "planets")
    private List<Planet> list;

    public Universe() {
        list = new ArrayList<Planet>();
    }

    public void add(Planet p) {
        list.add(p);
    }
    public void getSortPlanetName() {
            this.list.sort(Comparator.comparing(Planet::getName));
            for(Planet p : this.list){
            System.out.println(p.getName());}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Planet pl : list) {
            sb.append(pl.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public void getNameAndPlanet(Jedi jedi) {
        for (Planet l : list) {
            if (l.jedis.contains(jedi)) {
                System.out.println(l.getName() + " - " + jedi.toString());
            }
        }

    }
    public void getJedisFromTwoPlanets(String p1, String p2){
        Map<String, List<Jedi>> pName = new HashMap<>();
        for(Planet p : list){
            pName.put(p.getName(),p.jedis);
        }
        StringBuilder sb = new StringBuilder();
        if(pName.containsKey(p1) && pName.containsKey(p2)){
            sb.append(pName.get(p1).toString()).append(System.lineSeparator());
            sb.append(pName.get(p2).toString());
            System.out.println(sb.toString().trim());
        }else {
            throw new PlanetException("This planet does not exists");
        }
    }

}
