package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PR123sobreescriu extends Matrix {
    public static void main(String[] args) {
        PR123sobreescriu se = new PR123sobreescriu();
        se.crearArxiuFrases();
        se.sobreescriuArxiuMatrix();
    }
    public void sobreescriuArxiuMatrix() {
        String basePath = System.getProperty("user.dir") + "/Matrix/";
        String filePath = basePath + "frasesMatrix.txt";

        // Comprovar si existeix el directori i l'arxiu
        try {
            File dir = new File(basePath);
            if (!dir.exists()) {
                System.out.println("El directori \""+dir.getName()+"\" no existeix");
            }else{
                File f = new File(filePath);
                if (!f.exists()) {
                    System.out.println("L'arxiu \""+f.getName()+"\" no existeix");
                }else{
                    // Si existeix sobreescrivim les dades
                    FileWriter fw = new FileWriter(filePath);
                    fw.write("Hem sobreescrit les frases de Matrix");
                    fw.close();
                    
                    llegirArxiu(f);
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }

    
}
