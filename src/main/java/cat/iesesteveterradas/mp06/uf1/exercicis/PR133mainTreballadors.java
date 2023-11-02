package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors extends UtilsCSV {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String fileName = "PR133treballadors.csv";
        String filePath = basePath + fileName;

        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
                return;
            }
        }

        List<String> csv = UtilsCSV.read(filePath);

        System.out.println("Benvingut al gestor de treballadors.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introdueix l'identificador del treballador: ");
        String id = scanner.nextLine();
        int lineNumber = UtilsCSV.getLineNumber(csv, "Id", id);

        if (lineNumber == -1) {
            System.out.println("No s'ha trobat cap treballador amb l'identificador proporcionat.");
        } else {
            System.out.println("Dades actuals del treballador:");
            UtilsCSV.list(Arrays.asList(csv.get(lineNumber)));

            System.out.print("Quina dada vols modificar (Nom, Cognom, Departament o Salari): ");
            String columna = scanner.nextLine();

            if (Arrays.asList("Nom", "Cognom", "Departament", "Salari").contains(columna)) {
                System.out.print("Introdueix el nou valor: ");
                String nouValor = scanner.nextLine();

                UtilsCSV.update(csv, lineNumber, columna, nouValor);
                UtilsCSV.write(filePath, csv);

                System.out.println("Les dades s'han actualitzat amb èxit.");
            } else {
                System.out.println("La columna especificada no és vàlida.");
            }
        }

        scanner.close();
    }
}
