package bg.tu_varna.sit;

import bg.tu_varna.sit.Exceptions.JediException;
import bg.tu_varna.sit.Exceptions.PlanetException;

import java.util.*;

public class Universe {
    private List<Planet> planet;
    private List<Jedi> jedi;
    private Map<String,List<Jedi>> universe;

    public Universe() {
        this.universe = new HashMap<>();
        this.planet = new ArrayList<>();
        this.jedi = new ArrayList<>();
    }

    public void addPlanet(String p){
        Planet planet = new Planet(p);
        this.planet.add(planet);
    }

    public void createJedi(String p,String name, String rank, int age, String color, double strength) {
        Jedi jedi = new Jedi(name,rank,age,color,strength);
        List<String> planetName = new ArrayList<>();
        List<String> jediName = new ArrayList<>();
        for (Planet n : this.planet) {
            planetName.add(n.getName());
        }
        for (List<Jedi> j : universe.values()) {
            for(Jedi jed : j ){
                jediName.add(jed.getName());
            }
        }
        if (planetName.contains(p)) {
            if (!this.universe.containsValue(jedi)) {
                if (!jediName.contains(name)) {
                    this.jedi.add(jedi);
                    this.jedi.sort(Comparator.comparing(Jedi::getName));
                    this.universe.put(p, this.jedi);
                } else {
                    throw new JediException("This jedi already exist on this planet");
                }
            } else {
                throw new JediException("This jedi already exist on other planet");
            }
        } else {
            System.out.println("this planet not exist");
        }
    }

    public void removeJedi(String plName,String name){
        List<String> planetName = new ArrayList<>();
        for (Planet n : this.planet) {
            planetName.add(n.getName());
        }
        List<String> jediName = new ArrayList<>();
        for (List<Jedi> j : universe.values()) {
            for(Jedi jed : j ){
                jediName.add(jed.getName());
            }
        }
        if(!planetName.contains(plName)) {
            throw new PlanetException("This planet not exist");
        }
        if (!jediName.contains(name)){
            throw new JediException("This jedi already deleted");
        }
        this.universe.remove(plName, this.jedi.removeIf(j -> j.getName().equals(name)));
    }

    public void iterate(String plName) {
        List<String> name = new ArrayList<>();
        name.addAll(universe.keySet());
        if (name.contains(plName)) {
            universe.forEach((k, v) -> {
                System.out.println("Key" + k);
                for (Jedi n : v) {
                    System.out.println(n.getName());
                }
            });
        }
    }
}