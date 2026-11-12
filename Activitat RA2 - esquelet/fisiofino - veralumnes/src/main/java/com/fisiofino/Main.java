package com.fisiofino;

import java.io.IOException;

import com.fisiofino.controlador.Controller;
import com.fisiofino.database.Connexio;
import com.fisiofino.database.GestioPacients;
import com.fisiofino.vista.MenuView;

public class Main {
    public static void main(String[] args) {
        try {
            Connexio.iniciaConnexio();
            MenuView vista = new MenuView();
            GestioPacients gestioPacients = new GestioPacients();
            Controller conT = new Controller(vista,gestioPacients);
            conT.inicia();
        } catch (IOException e) {
            System.out.println("No s'ha pogut carregar la configuració: " + e.getMessage());
        } finally {
            Connexio.tancaConnexio();
        }
    }
}