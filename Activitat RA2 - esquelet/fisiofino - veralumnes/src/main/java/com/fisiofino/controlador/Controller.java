package com.fisiofino.controlador;

import java.io.IOException;
import com.fisiofino.database.GestioPacients;
import com.fisiofino.vista.MenuView;

public class Controller {

    private final MenuView vista;
    private final GestioPacients gestiopacients;

    public Controller(MenuView vista, GestioPacients gestiopacients) {
        this.vista = vista;
        this.gestiopacients = gestiopacients;
    }

    public void inicia() {
        vista.mostraBenvinguda();
        boolean sortir = false;

        while (!sortir) {
            vista.MenuOpcions();
            try {
                String opcio = vista.demanaOpcio();
                if (opcio == null) break;

                switch (opcio.toUpperCase()) {
                    case "1": /************** Crida als mètodes que fan la carregarXml(); ************/
                    /*************** HAS DE COMPLETAR EL CODI ******************/
                        break;
                    case "2": /************** consultarInfoPersonal(); *******************************/ 
                    /*************** HAS DE COMPLETAR EL CODI ******************/
                        break;
                    case "3": /************** consultarInfoMedica(); *********************************/   
                    /*************** HAS DE COMPLETAR EL CODI ******************/
                        break;
                    case "4": /************** consultarTots(); ***************************************/         
                    /*************** HAS DE COMPLETAR EL CODI ******************/ 
                        break;
                    case "5": /************** modificarEstat(); **************************************/        
                    /*************** HAS DE COMPLETAR EL CODI ******************/
                        break;
                    case "X": sortir = true;                break;
                    default:  vista.mostraError("Opció no vàlida");
                }
            } catch (IOException e) {
                vista.mostraError("Error de lectura: " + e.getMessage());
                sortir = true;
            } catch (Exception e) {
                vista.mostraError(e.toString());
            }
        }
        
        vista.mostraMissatge("Fins aviat!");
    }


}
