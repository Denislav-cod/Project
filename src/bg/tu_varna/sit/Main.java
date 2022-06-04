package bg.tu_varna.sit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            Jedi vader = new Jedi("Vader", "BATTLE_MASTER", 12, "light", 1);
            Jedi anakin = new Jedi("Anakin", "BATTLE_MASTER", 25, "light", 2);
            Jedi luke = new Jedi("Luke", "PADAWAN", 2, "black", 3);
            Jedi obi = new Jedi("Obi", "YOUNGLING", 2, "green", 4);
            Jedi bob = new Jedi("Bob", "MASTER", 2, "green", 5);
            Jedi greg = new Jedi("Green", "PADAWAN", 3, "green", 6);
            Planet naboo = new Planet("Naboo");
            Planet kamino = new Planet("Kamino");
            kamino.addJediToPlanet(vader);
            kamino.addJediToPlanet(anakin);
            kamino.getStrongestJedi();
            naboo.addJediToPlanet(obi);
            naboo.addJediToPlanet(luke);
            naboo.addJediToPlanet(greg);
            luke.promoteJedi();
            vader.demoteJedi();
            kamino.getStrongestJedi();
            naboo.getMostUsedColorSaber();
            Universe list = new Universe();
            list.add(kamino);
            list.add(naboo);
            File file = new File("universe.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Universe.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(list, file);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            list.getNameAndPlanet(luke);
            list.getJedisFromTwoPlanets("Naboo","Kamino");
           System.out.println(jaxbUnmarshaller.unmarshal(file).toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}

