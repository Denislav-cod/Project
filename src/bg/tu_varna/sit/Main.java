package bg.tu_varna.sit;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Jedi vader = new Jedi("Vader", "BATTLE_MASTER", 12, "light", 1999.999);
        Jedi luke = new Jedi("luke", "PADAWAN", 2, "light", 1653.5124);
        Jedi obi = new Jedi("obi", "PADAWAN", 2, "light", 1653.5124);
        Planet embo = new Planet("embo");
        Planet planet = new Planet("Corusant");
        planet.addJediToPlanet(planet, vader);
        planet.addJediToPlanet(planet, luke);
        embo.addJediToPlanet(embo, obi);
        embo.addJediToPlanet(embo, luke);
        System.out.println(planet);

    }

}
