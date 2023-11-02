package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class PR124linies {
    public static void main(String[] args) {
        PR124linies ln = new PR124linies();
        ln.generaArxiuNumerosAleatoris();
    }
    public void generaArxiuNumerosAleatoris() {
        String basePath = System.getProperty("user.dir") + "/data/numeros/";
        String filePath = basePath + "numeros.txt";

        // Creem el directori numeros
        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'numeros'");
            }
        }
        // Creem l'arxiu amb la ruta i escrivim en aquest els numeros generats aleatoriament
        Random random = new Random();
        File f = new File(filePath);
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < 10; i++) {
                fw.write(random.nextInt(100) + "\n");
            }
            fw.close();
            llegirArxiu(f);
        } catch (IOException e) {e.printStackTrace();}

    }
    public void llegirArxiu(File arxiu) {
        // Funció per llegir l'arxiu
        try {
            System.out.println("Llegint l'arxiu numeros.txt\n");
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
