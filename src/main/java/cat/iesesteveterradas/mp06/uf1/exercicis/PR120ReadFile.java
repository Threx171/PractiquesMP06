package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.util.Scanner;

public class PR120ReadFile {
    public static void main(String[] args) {
        int lineNumber = 1;
        String basePath = System.getProperty("user.dir") + "/src/main/java/cat/iesesteveterradas/mp06/uf1/exercicis/";
        String fileName = "PR120ReadFile.java";
        String filePath = basePath + fileName;

        File text = new File(filePath);
        Scanner scan;
        try {
            scan = new Scanner(text);
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println("line " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
