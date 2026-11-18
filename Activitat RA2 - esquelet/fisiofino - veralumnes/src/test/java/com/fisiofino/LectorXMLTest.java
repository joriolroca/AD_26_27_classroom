package com.fisiofino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fisiofino.model.MalaltiaPacient;
import com.fisiofino.model.Pacient;

class LectorXMLTest {

  // ---------- Pacients (<patientRole>) ---------- //

  private Pacient primerPacientDelFitxer() throws Exception {
    return LectorXML.llegirPacients(Path.of("pacientes_bluebutton.xml")).get(0);
  }

  @Test
  void llegeixElNom() throws Exception {
    assertEquals("Laura", primerPacientDelFitxer().getGivenName(),
        "El nom de pila s'ha de llegir de <given>");
  }

  @Test
  void llegeixElsCognoms() throws Exception {
    assertEquals("Exemple Roca", primerPacientDelFitxer().getFamilyName(),
        "Els cognoms s'han de llegir de <family>");
  }

  @Test
  void llegeixElDni() throws Exception {
    assertEquals("88999121H", primerPacientDelFitxer().getDni(),
        "El DNI/NIE s'ha de llegir de <dni>");
  }

  @Test
  void llegeixLaDataDeNaixement() throws Exception {
    assertEquals(LocalDate.of(1985, 3, 14), primerPacientDelFitxer().getBirthDate(),
        "La data s'ha de llegir de l'atribut value de <birthTime>");
  }

  @Test
  void llegeixElGenere() throws Exception {
    assertEquals("F", primerPacientDelFitxer().getGender(),
        "El gènere s'ha de llegir de l'atribut code de <administrativeGenderCode>");
  }

  @Test
  void llegeixLAdreca() throws Exception {
    assertEquals("Carrer de l'Exemple, 12, 3r 1a", primerPacientDelFitxer().getAddress(),
        "L'adreça s'ha de llegir de <streetAddressLine>");
  }

  @Test
  void llegeixLaCiutat() throws Exception {
    assertEquals("Barcelona", primerPacientDelFitxer().getCity(),
        "La ciutat s'ha de llegir de <city>");
  }

  @Test
  void llegeixElCodiPostal() throws Exception {
    assertEquals("08001", primerPacientDelFitxer().getPostalCode(),
        "El codi postal s'ha de llegir de <postalCode>");
  }

  @Test
  void llegeixElPais() throws Exception {
    assertEquals("ES", primerPacientDelFitxer().getCountry(),
        "El país s'ha de llegir de <country>");
  }

  @Test
  void llegeixElTelefon() throws Exception {
    assertEquals("+34-600-000-000", primerPacientDelFitxer().getTelephone(),
        "El telèfon és l'atribut value de <telecom> sense el prefix \"tel:\"");
  }

  @Test
  void llegeixElCorreu() throws Exception {
    assertEquals("laura.martinez@example.com", primerPacientDelFitxer().getEmail(),
        "El correu és l'atribut value de <telecom> sense el prefix \"mailto:\"");
  }

  // ---------- Malalties (<problem>) ---------- //

  private List<MalaltiaPacient> malaltiesDelFitxer() throws Exception {
    return LectorXML.llegirMalalties(Path.of("pacientes_bluebutton.xml"));
  }

  private MalaltiaPacient primeraMalaltia() throws Exception {
    return malaltiesDelFitxer().get(0);
  }

  private MalaltiaPacient segonaMalaltia() throws Exception {
    return malaltiesDelFitxer().get(1);
  }

  @Test
  void llegeixLesMalalties() throws Exception {
    assertFalse(malaltiesDelFitxer().isEmpty(),
        "Cada <problem> s'ha de convertir en una MalaltiaPacient");
  }

  @Test
  void llegeixElCodiDeLaMalaltia() throws Exception {
    assertEquals("38341003", primeraMalaltia().getCode(),
        "El codi s'ha de llegir de l'atribut code de <code> dins de <problem>");
  }

  @Test
  void llegeixElNomDeLaMalaltia() throws Exception {
    assertEquals("Hipertensión arterial", primeraMalaltia().getName(),
        "El nom s'ha de llegir del text de <code> dins de <problem>");
  }

  @Test
  void llegeixLEstat() throws Exception {
    assertEquals("Activo", primeraMalaltia().getStatus(),
        "L'estat s'ha de llegir del text de <status>");
  }

  @Test
  void llegeixElTractamentAmbUnaSolaMedicacio() throws Exception {
    assertEquals("Enalapril 10 mg, Cada 24 h", primeraMalaltia().getTreatment(),
        "El tractament és \"medicació dosi unitat, freqüència\" (<medication>, <dose>, <frequency>)");
  }

  @Test
  void llegeixElTractamentAmbVariesMedicacions() throws Exception {
    assertEquals("Budesonida inhalada 200 mcg, Cada 12 h; Salbutamol inhalado 100 mcg, A demanda",
        segonaMalaltia().getTreatment(),
        "Els <treatment> d'un mateix <problem> es separen amb \"; \" en un sol text");
  }

  @Test
  void llegeixElDniDelPacientDeLaMalaltia() throws Exception {
    assertEquals("88999121H", primeraMalaltia().getDniPacient(),
        "El DNI del pacient s'ha de copiar del <dni> del <recordTarget> anterior");
  }

  // ---------- Tot el fitxer (diversos pacients) ---------- //

  @Test
  void llegeixTotsElsPacients() throws Exception {
    List<Pacient> pacients = LectorXML.llegirPacients(Path.of("pacientes_bluebutton.xml"));
    assertEquals(5, pacients.size(), "Hi ha un pacient per cada <ClinicalDocument>");
    assertEquals("Pere", pacients.get(1).getGivenName());
    assertEquals("Manel", pacients.get(4).getGivenName());
    assertEquals("46464678J", pacients.get(4).getDni());
  }

  @Test
  void elsPacientsNoBarregenLesDades() throws Exception {
    Pacient marta = LectorXML.llegirPacients(Path.of("pacientes_bluebutton.xml")).get(2);
    assertEquals("Marta", marta.getGivenName());
    assertEquals("75999121M", marta.getDni());
    assertEquals(LocalDate.of(1990, 6, 7), marta.getBirthDate());
    assertEquals("F", marta.getGender());
  }

  @Test
  void llegeixTotesLesMalalties() throws Exception {
    assertEquals(13, malaltiesDelFitxer().size(), "Hi ha una MalaltiaPacient per cada <problem>");
  }

  @Test
  void cadaMalaltiaTeElDniDelSeuPacient() throws Exception {
    List<MalaltiaPacient> malalties = malaltiesDelFitxer();
    // 2 malalties del pacient 1, 3 del 2, 2 del 3, 3 del 4 i 3 del 5
    assertEquals("44999121S", malalties.get(2).getDniPacient());
    assertEquals("75999121M", malalties.get(5).getDniPacient());
    assertEquals("46464678M", malalties.get(7).getDniPacient());
    assertEquals("46464678J", malalties.get(10).getDniPacient());
    assertEquals("46464678J", malalties.get(12).getDniPacient());
  }

  @Test
  void llegeixUnaMalaltiaDelTercerPacient() throws Exception {
    MalaltiaPacient resfriat = malaltiesDelFitxer().get(6);
    assertEquals("82272006", resfriat.getCode());
    assertEquals("Resfriado común", resfriat.getName());
    assertEquals("Resuelto", resfriat.getStatus());
  }
}
