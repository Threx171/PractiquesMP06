package cat.iesesteveterradas.mp06.uf1.exercicis;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Attr;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class PR141Main {
    public static void main(String[] args) {
        try {
            String basePath = System.getProperty("user.dir") + "/data/";
            String fileName = "biblioteca.xml";
            String filePath = basePath + fileName;

            // Crear la carpeta 'data' si no existeix
            File dir = new File(basePath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    System.out.println("Error en la creació de la carpeta 'data'");
                    return;
                }
            }

            // Crea una factoria de constructors de documents
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Crea un constructor de documents
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Crea un nou document XML
            Document doc = db.newDocument();

            // Crea l'element root del document XML
            Element bibliotecaElement = doc.createElement("biblioteca");
            doc.appendChild(bibliotecaElement);

            // Crea l'element "llibre" amb atribut "id"
            Element llibreElement = doc.createElement("llibre");
            Attr attrId = doc.createAttribute("id");
            attrId.setValue("001");
            llibreElement.setAttributeNode(attrId);
            bibliotecaElement.appendChild(llibreElement);

            // Crea l'element "titol" i afegeix contingut de text
            Element titolElement = doc.createElement("titol");
            Text nodeTextTitol = doc.createTextNode("El viatge dels venturons");
            titolElement.appendChild(nodeTextTitol);
            llibreElement.appendChild(titolElement);

            // Crea l'element "autor" i afegeix contingut de text
            Element autorElement = doc.createElement("autor");
            Text nodeTextAutor = doc.createTextNode("Joan Pla");
            autorElement.appendChild(nodeTextAutor);
            llibreElement.appendChild(autorElement);

            // Crea l'element "anyPublicacio" i afegeix contingut de text
            Element anyPublicacioElement = doc.createElement("anyPublicacio");
            Text nodeTextAnyPublicacio = doc.createTextNode("1998");
            anyPublicacioElement.appendChild(nodeTextAnyPublicacio);
            llibreElement.appendChild(anyPublicacioElement);

            // Crea l'element "editorial" i afegeix contingut de text
            Element editorialElement = doc.createElement("editorial");
            Text nodeTextEditorial = doc.createTextNode("Edicions Mar");
            editorialElement.appendChild(nodeTextEditorial);
            llibreElement.appendChild(editorialElement);

            // Crea l'element "genere" i afegeix contingut de text
            Element genereElement = doc.createElement("genere");
            Text nodeTextGenere = doc.createTextNode("Aventura");
            genereElement.appendChild(nodeTextGenere);
            llibreElement.appendChild(genereElement);

            // Crea l'element "pagines" i afegeix contingut de text
            Element paginesElement = doc.createElement("pagines");
            Text nodeTextPagines = doc.createTextNode("320");
            paginesElement.appendChild(nodeTextPagines);
            llibreElement.appendChild(paginesElement);

            // Crea l'element "disponible" i afegeix contingut de text
            Element disponibleElement = doc.createElement("disponible");
            Text nodeTextDisponible = doc.createTextNode("true");
            disponibleElement.appendChild(nodeTextDisponible);
            llibreElement.appendChild(disponibleElement);

            // Crea un objecte Transformer per desar el document com a fitxer XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            // Guarda el document com a fitxer XML
            transformer.transform(source, result);

            System.out.println("El fitxer 'biblioteca.xml' ha estat creat amb èxit a la ubicació: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
