package bg.tu_varna.sit;

import java.util.*;

public class Planet {
    private String name;
    private Map<Planet, List<Jedi>> map;

    public Planet(String name) {
        this.name = name;
        this.map = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Planet, List<Jedi>> getMap() {
        map.forEach((k, v) -> {
            System.out.println(k + "Jedis");
            System.out.println("\n" + v + "\n");
        });
        return map;
    }

    public void removeJedi(Planet planet, Jedi jedi) {
        map.forEach((k, v) -> {
            v.remove(jedi);
        });
    }

    public void addJediToPlanet(Planet planet, Jedi jedi) {
        List<Jedi> jedis = map.get(planet);
        if (!map.containsValue(jedis)) {
            jedis = new ArrayList<Jedi>();
            jedis.add(jedi);
            map.put(planet, jedis);
        } else {
            jedis.add(jedi);
        }
    }

    @Override
    public String toString() {
        return "Planet" +
                " with name " + name + " and population of ";
    }

}
