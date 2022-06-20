package bg.tu_varna.sit;

import javax.xml.bind.JAXBException;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.menu();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}

