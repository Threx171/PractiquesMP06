package cat.iesesteveterradas.mp06.uf1.exercicis;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import java.io.File;

public class PR142Main {
    public static void main(String[] args) {
        try {
            String basePath = System.getProperty("user.dir") + "/data/";
            String fileName = "cursos.xml";
            String filePath = basePath + fileName;

            File xmlFile = new File(filePath);

            if (!xmlFile.exists()) {
                System.out.println("L'arxiu 'cursos.xml' no s'ha trobat a la ubicació: " + filePath);
                return;
            }


            // Carrega l'arxiu XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Inicialitza XPath
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            boolean running = true;
            while (running) {
                String menu = "\nEscull una opció: ";
                menu = menu + "\n 0) Llistar cursos";
                menu = menu + "\n 1) Llistar tutors";
                menu = menu + "\n 2) Llistar total estudiants";
                menu = menu + "\n 3) Llistar mòduls";
                menu = menu + "\n 4) Llistar estudiants";
                menu = menu + "\n 5) Afegir estudiant";
                menu = menu + "\n 6) Eliminar estudiant";
                menu = menu + "\n 100) Sortir";
                System.out.println(menu);

                int opcio = Integer.parseInt(Main.llegirLinia("Opció: "));
                try {
                    switch(opcio) {
                        case 0: listCourseIDs(document, xPath);; break;
                        case 1: listTutors(document, xPath); break;
                        case 2: listTotalStudents(document, xPath); break;
                        case 3: String courseId = "AMS2"; // Canvia a l'ID del curs desitjat
                            listModuleIDsAndTitles(document, xPath, courseId); break;
                        case 4: courseId = "AMS2";
                            listStudentsInCourse(document, xPath, courseId); break;
                        case 5: courseId = "AMS2"; String studentName = "FLORES, Pepe";
                            addStudentToCourse(document, xPath, courseId, studentName); break;
                        case 6: courseId = "AMS2"; String studentToRemove = "FLORES, Pepe";
                            removeStudentFromCourse(document, xPath, courseId, studentToRemove); break;
                        case 100: running = false; break;
                        default: break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funció per a llistar IDs de cursos
    private static void listCourseIDs(Document document, XPath xPath) throws Exception {
        NodeList nodes = (NodeList) xPath.evaluate("/cursos/curs/@id", document, XPathConstants.NODESET);

        System.out.println("IDs de cursos:");
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }

    // Funció per a llistar tutors
    private static void listTutors(Document document, XPath xPath) throws Exception {
        NodeList nodes = (NodeList) xPath.evaluate("/cursos/curs/tutor", document, XPathConstants.NODESET);

        System.out.println("Tutors:");
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getTextContent());
        }
    }

    // Funció per a llistar el total d'alumnes
    private static void listTotalStudents(Document document, XPath xPath) throws Exception {
        Double totalCount = (Double) xPath.evaluate("count(/cursos/curs/alumnes/alumne)", document, XPathConstants.NUMBER);

        System.out.println("Total d'alumnes: " + totalCount.intValue());
    }

    // Funció per a llistar IDs i títols dels mòduls a partir d'un ID de curs
    private static void listModuleIDsAndTitles(Document document, XPath xPath, String courseId) throws Exception {
        NodeList modules = (NodeList) xPath.evaluate("/cursos/curs[@id='" + courseId + "']/moduls/modul", document, XPathConstants.NODESET);

        System.out.println("Mòduls per al curs " + courseId + ":");
        for (int i = 0; i < modules.getLength(); i++) {
            Node module = modules.item(i);
            String moduleId = ((Element) module).getAttribute("id");
            String moduleTitle = xPath.evaluate("titol", module);
            System.out.println("ID: " + moduleId + ", Títol: " + moduleTitle);
        }
    }

    // Funció per a llistar alumnes d'un curs
    private static void listStudentsInCourse(Document document, XPath xPath, String courseId) throws Exception {
        NodeList students = (NodeList) xPath.evaluate("/cursos/curs[@id='" + courseId + "']/alumnes/alumne", document, XPathConstants.NODESET);

        System.out.println("Alumnes per al curs " + courseId + ":");
        for (int i = 0; i < students.getLength(); i++) {
            System.out.println(students.item(i).getTextContent());
        }
    }

    // Funció per afegir un alumne a un curs
    private static void addStudentToCourse(Document document, XPath xPath, String courseId, String studentName) throws Exception {
        Node studentsElement = (Node) xPath.evaluate("/cursos/curs[@id='" + courseId + "']/alumnes", document, XPathConstants.NODE);

        Element newStudentElement = document.createElement("alumne");
        newStudentElement.setTextContent(studentName);

        studentsElement.appendChild(newStudentElement);

        // Actualitza l'arxiu XML amb el nou alumne
        updateXML(document);
        System.out.println("S'ha afegit l'alumne " + studentName + " al curs " + courseId);
    }

    // Funció per eliminar un alumne d'un curs
    private static void removeStudentFromCourse(Document document, XPath xPath, String courseId, String studentName) throws Exception {
        String xpathExpr = "/cursos/curs[@id='" + courseId + "']/alumnes/alumne[text()='" + studentName + "']";
        Node studentNode = (Node) xPath.evaluate(xpathExpr, document, XPathConstants.NODE);

        if (studentNode != null) {
            studentNode.getParentNode().removeChild(studentNode);

            // Actualitza l'arxiu XML sense l'alumne eliminat
            updateXML(document);
            System.out.println("S'ha eliminat l'alumne " + studentName + " del curs " + courseId);
        } else {
            System.out.println("L'alumne " + studentName + " no existeix en el curs " + courseId);
        }
    }

    // Funció per desar les actualitzacions a l'arxiu XML
    private static void updateXML(Document document) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("data/cursos.xml"));
        transformer.transform(source, result);
    }
}