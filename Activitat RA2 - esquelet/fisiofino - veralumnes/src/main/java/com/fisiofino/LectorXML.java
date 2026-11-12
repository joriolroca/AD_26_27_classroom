package com.fisiofino;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fisiofino.model.MalaltiaPacient;
import com.fisiofino.model.Pacient;

/**
 * Llegeix un XML amb DOM i en mostra el contingut sense conèixer-ne l'estructura.
 *
 * Important: amb DOM, builder.parse() llegeix TOT el fitxer i construeix l'arbre
 * sencer en memòria. Només després el recorrem i el mostrem.
 */
public class LectorXML {
    static Pacient p;

    static MalaltiaPacient mp;

    public static List<Pacient> llegirPacients(Path entradaXML) throws IOException, ParserConfigurationException, SAXException {
            Document doc = obtenirDocument(entradaXML);
            Element arrel = doc.getDocumentElement();
            List<Pacient> pacients = new ArrayList<>();

            // Comencem el recorregut recursiu per l'arrel, al nivell 0
            recorrer(arrel, 0,pacients,new ArrayList<>());
            return pacients;
    }

    public static List<MalaltiaPacient> llegirMalalties(Path entradaXML) throws IOException, ParserConfigurationException, SAXException {
            Document doc = obtenirDocument(entradaXML);
            Element arrel = doc.getDocumentElement();
            List<MalaltiaPacient> malalties = new ArrayList<>();

            // Comencem el recorregut recursiu per l'arrel, al nivell 0
            recorrer(arrel, 0,new ArrayList<>(),malalties);
            return malalties;
    }

    /**
     * Carrega el fitxer XML (Path de NIO) i retorna l'arbre DOM.
     * parse() no accepta un Path, per això li passem un InputStream de NIO.
     */
    public static Document obtenirDocument(Path entradaXML)
            throws IOException, ParserConfigurationException, SAXException {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        try (InputStream is = Files.newInputStream(entradaXML)) {
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();   // fusiona nodes de text adjacents
            return doc;
        }
    }

    /**
     * Recorre recursivament l'arbre a partir d'un node.
     *  - Cas base 1: el node no és un element (text de la indentació) -> no fem res.
     *  - Cas base 2: element fulla (només conté text) -> el mostrem i tornem.
     *  - Cas recursiu: element pare -> el mostrem i explorem els seus fills.
     *
     * node:   node que estem visitant
     * nivell: profunditat dins l'arbre (0 = arrel, 1 = registres com <alumne>..., 
     *                                   2 = registres com <nom>)
     */
    public static void recorrer(Node node, int nivell, List<Pacient> pacients, List<MalaltiaPacient> malPacients) {
        
        // Cas base 1: només tractem elements; els nodes de text de la indentació s'ignoren
        if (node.getNodeType() != Node.ELEMENT_NODE) return;

        // Afegim els atributs de l'element (p. ex. id), sigui quin sigui el seu nom
        NamedNodeMap atributs = node.getAttributes();
        for (int i = 0; i < atributs.getLength(); i++) {
            Node at = atributs.item(i);
            if (node.getNodeName().equals("telecom")) {
/* AQUÍ TENIU UN EXEMPLE DE COM EXTREURE INFORMACIÓ D'ATRIBUTS
                 if (at.getTextContent().contains("tel")) {
                    p.setTelefon(at.getTextContent().substring(4));
                }
                if (at.getTextContent().contains("mailto")) {
                    p.setMail(at.getTextContent().substring(7));
                } */
            }
            /******************** HAS DE COMPLETAR EL CODI ***************/
        }
 
        // Cas base 2: element fulla (nom, cognoms...) -> mostrem nom i text a la mateixa línia
        if (esFulla(node)) {
/* AQUÍ TENIU UN EXEMPLE DE COM EXTREURE EL TEXT DELS ELEMENTS
             if (node.getNodeName().equals("given")) {
                p.setNom(node.getTextContent());
            } */
            return;
        /****************** HAS DE COMPLETAR EL CODI ********************/
        }

        // Element pare significa que creem un nou pacient
        if (nivell == 1) {
            p = new Pacient();
        } 

        if (node.getNodeName().equals("problem")) {
            mp = new MalaltiaPacient();

           /* DESCOMENTEU SI HO COMENTEU NECESSARI, dependrà de la vostra implementació  
           mp.setDniPacient(p.getDniNie());  */  // la informació del pacient <recordTarget> apareix abans dels <problem>
        }

        // Cas recursiu: explorem els fills un nivell més avall
        NodeList fills = node.getChildNodes();
        for (int i = 0; i < fills.getLength(); i++) {
            recorrer(fills.item(i), nivell + 1,pacients,malPacients);
        }

        if (node.getNodeName().equals("problem")) {
            malPacients.add(mp);
            mp = null;
        }
        if (nivell == 1) {
            pacients.add(p);
            p = null;
        }
    }

    /**
     * Un element és fulla si no té cap fill de tipus element (només text, o res).
     * Atenció: un element buit també es considera fulla.
     */
    static boolean esFulla(Node node) {
        NodeList fills = node.getChildNodes();
        for (int i = 0; i < fills.getLength(); i++) {
            if (fills.item(i).getNodeType() == Node.ELEMENT_NODE) return false;
        }
        return true;
    }
}
