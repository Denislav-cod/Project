package bg.tu_varna.sit;


import bg.tu_varna.sit.Exceptions.JediException;
import bg.tu_varna.sit.Exceptions.PlanetException;

import java.util.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "universe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Universe {
    private final List<Planet> planets;
    private List<String> plNames = new ArrayList<>();
    public Universe(){
        this.planets = new ArrayList<>();
    }

    public  void addPlanet(String plName){
        Planet planet = new Planet(plName);
        this.plNames.add(plName);
        for(Planet pl : planets){
            if(pl.getName().equals(plName)){
                throw new PlanetException("This planet exists");
            }
        }
        this.planets.add(planet);
        this.planets.sort(Comparator.comparing(Planet::getName));
    }


    public void createJedi(String plName, String jName, String rank, int age, String color, double strength) {
        Jedi jedi = new Jedi(plName, jName, rank.toUpperCase(), age, color, strength);
        for (Planet pl : this.planets) {
            for (Jedi j : pl.getJedis()) {
                if (j.getName().equals(jName)) {
                    throw new JediException("This jedi already exist");
                }
            }
            if(!this.plNames.contains(plName)){
               throw new PlanetException("This planet not exist");
            }
            if(pl.getName().equals(jedi.getPlanet())){
                pl.addJedis(jedi);
            }
        }
    }
    public void removeJedi(String plName, String jName){
        for(Planet pl : planets){
            if(pl.getName().equals(plName)){
                pl.removeJedi(jName);
                System.out.println("Successfully deleted jedi");
            }
        }
    }
    public  void promoteJedi(String jName, Double multiplier){
        for(Planet pl : planets) {
            for (Jedi j : pl.getJedis()) {
                if (j.getName().equals(jName)) {
                    pl.promoteJedi(jName,multiplier);
                    System.out.println("Successfully promoted jedi");
                }
            }
        }
    }
    public  void demoteJedi(String jName, Double multiplier){
        for(Planet pl : planets) {
            for (Jedi j : pl.getJedis()) {
                if (j.getName().equals(jName)) {
                    pl.demoteJedi(jName,multiplier);
                    System.out.println("Successfully demoted jedi");
                }
            }
        }
    }

    public void getStrongestJedi(String plName){
        for(Planet pl : planets) {
            if(pl.getName().equals(plName)){
                pl.getStrongestJedi();
            }
        }
    }

    public void getYoungestJedi(String plName, String rank){
        for(Planet pl : planets) {
            if(pl.getName().equals(plName)){
                pl.getYoungestJedi(rank.toUpperCase());
            }
        }
    }
    public void getMostUsedSaberColor(String plName){
        for(Planet pl : planets) {
            if(pl.getName().equals(plName)){
                pl.getMostUsedSaberColor();
            }
        }
    }
    public void getMostUsedSaberColor(String plName, String rank){
        for(Planet pl : planets) {
            if(pl.getName().equals(plName)){
                pl.getMostUsedSaberColor(rank.toUpperCase());
            }
        }
    }

    public void printPlanetInfo(String plName){
        if(!this.plNames.contains(plName)){
            throw new PlanetException("This planet not exist");
        }
        for(Planet pl : planets){
            if(pl.getName().equals(plName)){
                System.out.println(pl);
            }
        }
    }

    public void printJediInfo(String jName){
        for(Planet pl : planets){
            for(Jedi j : pl.getJedis()){
                if(j.getName().equals(jName)){
                    System.out.println(j);
                }
            }
        }
    }

    public void printTwoPlanetsInfo(String plNameOne, String plNameTwo){
        printPlanetInfo(plNameOne);
        printPlanetInfo(plNameTwo);
    }
}
