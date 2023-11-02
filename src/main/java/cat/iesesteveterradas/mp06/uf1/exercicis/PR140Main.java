package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// En aquest exemple es fa servir
// UtilsXML per llegir i modificar
// les dades d'un XML

public class PR140Main {
    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String fileName = "persones.xml";
        String filePath = basePath + fileName;

        // Crear la carpeta 'data' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        System.out.println("");

        Document doc = UtilsXML.read(filePath);

        System.out.println("Llista de persones:");
        NodeList llista0 = UtilsXML.getNodeList(doc, "/persones/persona");
        printLlistaPersones(llista0);

    }

    static void printLlistaPersones (NodeList llista) {
        for(int cnt = 0; cnt < llista.getLength(); cnt = cnt + 1) {
            Node nodePersona = llista.item(cnt);
            if(nodePersona.getNodeType() == Node.ELEMENT_NODE) {
                // Si és de tipus "ELEMENT_NODE" podem fer el cast a Element
                Element elmPersona = (Element) nodePersona;
                String attrId = elmPersona.getTagName();
                Element childName = UtilsXML.getFirstChildByName(elmPersona, "nom");
                Element childSurname = UtilsXML.getFirstChildByName(elmPersona, "cognom");
                Element childEdat = UtilsXML.getFirstChildByName(elmPersona, "edat");
                Element childCiutat = UtilsXML.getFirstChildByName(elmPersona, "ciutat");
                String txtName = childName.getTextContent();
                String txtSurname = childSurname.getTextContent();
                String txtEdat = childEdat.getTextContent();
                String txtCiutat = childCiutat.getTextContent();
                System.out.println(attrId.toUpperCase() + "\n\t" + txtName + "\n\t" + txtSurname+
                        "\n\t" + txtEdat + "\n\t" + txtCiutat);
            }
        }
    }
}
