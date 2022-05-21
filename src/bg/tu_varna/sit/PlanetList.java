package bg.tu_varna.sit;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;
@XmlRootElement(name = "planets")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanetList {
    @XmlElement(name = "planet")
    private List<Planet> list;
    public PlanetList(){
        list = new ArrayList<Planet>();
    }

    public void add(Planet p){
        list.add(p);
    }
}
