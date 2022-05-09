package bg.tu_varna.sit;

import java.util.*;


public class Planet {
    private String name;
    private List<Jedi> jedis;

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

    public void removeJedi(Jedi jedi) {
        this.jedis.remove(jedi);
        System.out.println("Success Delete");
    /*    map.forEach((k, v) -> {
            v.remove(jedi);
        });*/
    }


    public void addJediToPlanet(Jedi jedi) {
        /*List<Jedi> jedis = map.get(planet);
        if (map.containsKey(planet.getName())) {
            throw new CreatePlanet("First create planet with this NAME");
        } else {
            if (!map.containsValue(jedis)) {
                jedis = new ArrayList<>();
                jedis.add(jedi);
                map.put(planet, jedis);
            } else {
                jedis.add(jedi);
            }
        }*/
        if (jedis.contains(jedi)) {
            throw new AlreadyTaken("This jedi already exists on this planet");
        } else {
            this.jedis.add(jedi);
        }
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
