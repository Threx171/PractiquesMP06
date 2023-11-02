package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Matrix {

    public void crearArxiuFrases() {
        String basePath = System.getProperty("user.dir") + "/Matrix/";
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'Matrix'");
            }
        }
        System.out.println("");
        try {
            File fMatrix = new File(basePath + "frasesMatrix.txt");
            String filePath = basePath + fMatrix.getName();
            
            FileWriter fw0 = new FileWriter(filePath);
            fw0.write("Yo sólo puedo mostrarte la puerta\n");
            fw0.write("Tú eres quien la tiene que atravesar");
            fw0.close();

            llegirArxiu(fMatrix);


        } catch ( IOException e) {e.printStackTrace();}
    }
    public void llegirArxiu(File arxiu) {
        try {
            System.out.println("Llegint l'arxiu frasesMatrix.txt\n");
            Scanner scn = new Scanner(arxiu);
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                System.out.println(line);
            }
            scn.close();
            System.out.println("*********************************\n");
        }catch (IOException e) {e.printStackTrace();}
    }
}
