package bg.tu_varna.sit;

import bg.tu_varna.sit.Exceptions.JediException;
import bg.tu_varna.sit.Exceptions.PlanetException;
import bg.tu_varna.sit.Exceptions.RankException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Universe universe = new Universe();
    private File file;
    private String path;
    private boolean open = false;

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void close() {
        if (!isOpen()) {
            System.out.println("First  open the file");
        } else {
            this.file = null;
            this.path = null;
            this.universe = new Universe();
            setOpen(false);
            System.out.println("You successful closed the file");
        }
    }

    public void open(String path) throws IOException, JAXBException {
        if (isOpen() && getPath().equals(path)) {
            System.out.println("You already open this file");
        } else {
            File file = new File(path);
            if (file.exists()) {
                FileInputStream input = new FileInputStream(path);
                if (input.available() != 0) {
                    JAXBContext jaxbContext = JAXBContext.newInstance(Universe.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//                    this.universe = (Universe) unmarshaller.unmarshal(file);
                    setUniverse((Universe) unmarshaller.unmarshal(file));
                    input.close();
                }
                System.out.println("File is opened " + file.getName());
            } else {
                if (file.createNewFile()) {
                    System.out.println("You have created new file");
                }
            }
            this.file = file;
            this.path = path;
            setOpen(true);
        }
    }

    public void save() throws IOException, JAXBException {
        if (isOpen()){
            JAXBContext jaxbContext = JAXBContext.newInstance(Universe.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(universe, getFile());
            System.out.println("Successfully saved " + this.file.getName());
        } else {
            System.out.println("First you need to open a file!");
        }
    }
    public void saveAs(String path) {
        if (isOpen()) {
            File file = new File(path);
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Universe.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                jaxbMarshaller.marshal(universe, file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            System.out.println("Successfully saved " + file.getName());
        } else {
            System.out.println("First you need to open a file!");
        }
    }

    public void help() {
        System.out.println("\nThis menu handle the following commands" +
                "\nopen - opens the file" +
                "\nsave - save the open file " +
                "\nsave_as - save the open file in new file " +
                "\nadd_planet <planet_name> - create new planet" +
                "\ncreate_jedi <planet_name> <jedi_name> <jedi_rank<jedi_age> <saber_color <jedi_strength> - create new jedi " +
                "\nremove_jedi <jedi_name> <planet_name> - remove jedi from planet " +
                "\npromote_jedi <jedi_name> <multiplier> - promoto jedi and increase his strength " +
                "\ndemote_jedi <jedi_name> <multiplier> - demote jedi and decrease his strength " +
                "\nget_strongest_jedi <planet_name> - get the strongest jedi" +
                "\nget_youngest_jedi <planet_name> - get the youngest jedi" +
                "\nclose - close the file" +
                "\nquit - close the program");
    }

    public void menu() throws JAXBException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter command: ");
        String enter = scanner.nextLine();
        while (!enter.equals("exit")) {
            String[] orders = enter.split("\\s+");
            String path;
            String plName;
            String secondPlName;
            String jName;
            String rank;
            String color;
            int age;
            double strength;
            double multiplier;
            switch (orders[0].toLowerCase()) {
                case "open":
                    System.out.println("Enter file path:");
                    path = scanner.nextLine();
                    try{
                        open(path);
                    }catch (IOException | JAXBException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "close":
                    close();
                    break;
                case "add_planet":
                    System.out.println("Enter name of the planet");
                    plName = scanner.nextLine();
                    try {
                        universe.addPlanet(plName);
                    }catch (PlanetException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "create_jedi":
                    System.out.println("Enter name of the planet");
                    plName = scanner.nextLine();
                    System.out.println("Enter jedi name");
                    jName = scanner.nextLine();
                    System.out.println("Enter jedi rank");
                    rank = scanner.nextLine();
                    System.out.println("Enter jedi age");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter jedi color saber");
                    color = scanner.nextLine();
                    System.out.println("Enter jedi strength ");
                    strength = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        universe.createJedi(plName, jName, rank, age, color, strength);
                    } catch (RankException | JediException | PlanetException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "remove_jedi":
                    System.out.println("Enter planet name");
                    plName = scanner.nextLine();
                    System.out.println("Enter jedi name");
                    jName = scanner.nextLine();
                    try {
                        universe.removeJedi(plName, jName);
                    }catch (JediException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "promote_jedi":
                    System.out.println("Enter jedi name");
                    jName = scanner.nextLine();
                    System.out.println("Enter multiplier ");
                    multiplier = scanner.nextDouble();
                    scanner.nextLine();
                    universe.promoteJedi(jName,multiplier);
                    break;
                case "demote_jedi":
                    System.out.println("Enter jedi name");
                    jName = scanner.nextLine();
                    System.out.println("Enter multiplier ");
                    multiplier = scanner.nextDouble();
                    scanner.nextLine();
                    universe.demoteJedi(jName,multiplier);
                    break;
                case "get_youngest_jedi":
                    System.out.println("Enter planet name");
                    plName = scanner.nextLine();
                    System.out.println("Enter rank");
                    rank = scanner.nextLine();
                    universe.getYoungestJedi(plName, rank);
                    break;
                case "get_strongest_jedi":
                    System.out.println("Enter planet name");
                    plName = scanner.nextLine();
                    universe.getStrongestJedi(plName);
                    break;
                case "get_most_used_saber_color_by_rank":
                    System.out.println("Enter planet name");
                    plName = scanner.nextLine();
                    universe.getMostUsedSaberColor(plName);
                    break;
                case "get_most_used_saber_color":
                    System.out.println("Enter planet name");
                    plName = scanner.nextLine();
                    System.out.println("Enter jedi rank");
                    rank = scanner.nextLine();
                    universe.getMostUsedSaberColor(plName,rank);
                    break;
                case "print_planet":
                    System.out.println("Enter planet name");
                    plName = scanner.nextLine();
                    try {
                        getUniverse().printPlanetInfo(plName);
                    }catch (PlanetException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "print_two_planets":
                    System.out.println("Enter first planet name");
                    plName = scanner.nextLine();
                    System.out.println("Enter second planet name");
                    secondPlName = scanner.nextLine();
                    try {
                        universe.printTwoPlanetsInfo(plName,secondPlName);
                    }catch (PlanetException e){
                     System.out.println(e.getMessage());
                    }
                    break;
                case "save":
                    save();
                    break;
                case "save_as":
                    System.out.println("Enter path for the file");
                    path = scanner.nextLine();
                    saveAs(path);
                    break;
                case "help":
                    help();
                    break;
                default:
                    System.out.println("You have entered wrong command");
                    break;
            }
            System.out.print("Enter command: ");
            enter = scanner.nextLine();
        }
    }
}
