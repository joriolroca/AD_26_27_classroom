package com.fisiofino.database;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fisiofino.model.MalaltiaPacient;
import com.fisiofino.model.Pacient;

public class GestioPacients {

//Apartat 1: Insercions a partir del XML
    public int CreatePatient(Pacient p) throws SQLException {

        int idPacient = 0;

        /**************** HAS DE COMPLETAR EL CODI *******************/

        return idPacient;
    }

    public boolean CreateMalaltia(MalaltiaPacient mp) throws SQLException {

        boolean malaltiaCreada = false;

        /**************** HAS DE COMPLETAR EL CODI *******************/
        
        return malaltiaCreada;
    }    

    public boolean CreateMalaltiaPacient(MalaltiaPacient mp) throws SQLException {

        boolean malaltiaPacientCreada = false;

        /**************** HAS DE COMPLETAR EL CODI *******************/
        
        return malaltiaPacientCreada;
    }    

//Apartat 2: Cerca de pacient per DNI
    public Pacient findBydniNie(String dni_nie) throws SQLException {
            
        Pacient p = null;

        /**************** HAS DE COMPLETAR EL CODI *******************/

        return  p;
    }

//Apartat 3: Malalties del pacient
    public List<MalaltiaPacient> findDiseaseBydniNie(String dni_nie) throws SQLException {
        List<MalaltiaPacient> malaltiespacient = new ArrayList<MalaltiaPacient>();

        /**************** HAS DE COMPLETAR EL CODI *******************/    

        return malaltiespacient;
    }

//Apartat 4: Llistar tots els pacients
    public List<Pacient> findAllPacients () throws SQLException {
        List<Pacient> pacients = new ArrayList<>();

        /**************** HAS DE COMPLETAR EL CODI *******************/
        
        return pacients;
    }

//Apartat 5: Modificar l'estat d'una malaltia d'un pacient
    public boolean updateStatus (int idPacient, String snomedCode, String status) throws SQLException {
        boolean modificat = false;

        /**************** HAS DE COMPLETAR EL CODI *******************/

        return  modificat;
    }

// funcions i mètodes accesoris i necessaris per saber si s'ha d'insertar o modificar    
    public boolean findMalaltia (String SnomedCode) throws SQLException {
        boolean esMalaltia = false;
        
        /**************** HAS DE COMPLETAR EL CODI *******************/
        
        return esMalaltia;
    }

    public boolean findMalaltiaPacient (String dniPacient, String SnomedCode, String status) throws SQLException {
        boolean esMalaltiaPacient = false;

        /**************** HAS DE COMPLETAR EL CODI *******************/
        
        return esMalaltiaPacient;
    }


}
