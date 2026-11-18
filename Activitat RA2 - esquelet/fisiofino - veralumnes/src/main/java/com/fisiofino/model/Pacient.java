package com.fisiofino.model;

import java.time.LocalDate;

/*
 * Model (la "M" de MVC): representa un pacient de la clínica.
 * Cada objecte equival a una fila de la taula patient de la base de dades.
 *
 * És una classe de dades simple: té atributs, constructor, getters i toString, però cap
 * lògica ni accés a la base de dades (d'això s'ocupa GestioPacients).
 */

public class Pacient {

    public String getGivenName() {
        throw new UnsupportedOperationException("Unimplemented method 'getGivenName'");
    }

    public String getFamilyName() {
        throw new UnsupportedOperationException("Unimplemented method 'getFamilyName'");
    }

    public String getDni() {
        throw new UnsupportedOperationException("Unimplemented method 'getDni'");
    }

    public LocalDate getBirthDate() {
        throw new UnsupportedOperationException("Unimplemented method 'getBirthDate'");
    }

    public String getGender() {
        throw new UnsupportedOperationException("Unimplemented method 'getGender'");
    }

    public String getAddress() {
        throw new UnsupportedOperationException("Unimplemented method 'getAddress'");
    }

    public String getCity() {
        throw new UnsupportedOperationException("Unimplemented method 'getCity'");
    }

    public String getPostalCode() {
        throw new UnsupportedOperationException("Unimplemented method 'getPostalCode'");
    }

    public String getCountry() {
        throw new UnsupportedOperationException("Unimplemented method 'getCountry'");
    }

    public String getTelephone() {
        throw new UnsupportedOperationException("Unimplemented method 'getTelephone'");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }
}