package com.fisiofino.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.fisiofino.model.MalaltiaPacient;
import com.fisiofino.model.Pacient;

public class MenuView {

    private static BufferedReader brline = new BufferedReader(new InputStreamReader(System.in));

    public void mostraBenvinguda() {
        System.out.println("BENVINGUTS A FISIOFINO APP");
        tirarlinea();
    }

    public void mostraMissatge(String m) { System.out.println(m); }
    
    public void mostraError(String m)    { System.out.println("ERROR: " + m); }

    public void MenuOpcions() {
        System.out.println("Trieu una de les següents opcions o X per sortir");
        tirarlinea();
        System.out.println("1- Carregar informació dels fitxers xml");
        System.out.println("2- Consultar informació personal de pacient concret");
        System.out.println("3- Consultar informació mèdica de pacient concret");
        System.out.println("4- Consultar tots els pacients");
        System.out.println("5- Modificar estat de la malaltia del pacient concret");
    }

    public String demanaOpcio() throws IOException {
        return demana("OPCIÓ: ");
    }

    public String demana(String text) throws IOException {
        System.out.print(text);
        String linia = brline.readLine();
        if (linia != null) {
            linia = linia.trim();
        } 

        return linia;
    }

    public void mostraPacient(Pacient p) {
        tirarlinea();
        System.out.println(p);
        tirarlinea();
    }

    public void mostraMalaltiesPacient(List<MalaltiaPacient> mplist) {
        tirarlinea();
        for (MalaltiaPacient mp: mplist) {
            System.out.println(mp);
            tirarlinea();
        }
    }

    public void mostraPacients(List<Pacient> plist) {
        for (Pacient p: plist) {
            mostraPacient(p);
        }
    }

    private static void tirarlinea () {System.out.println("-".repeat(40)); }
}


