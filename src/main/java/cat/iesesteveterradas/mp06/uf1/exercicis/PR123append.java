package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PR123append extends Matrix{
    public static void main(String[] args) {
        PR123append ap = new PR123append();
        ap.crearArxiuFrases();
        ap.appendFrases();
    }
    public void appendFrases() {
        String basePath = System.getProperty("user.dir") + "/Matrix/";
        String filePath = basePath + "frasesMatrix.txt";
        try {
            // Comprovar si existeix el directori i l'arxiu
            File dir = new File(basePath);
            if (!dir.exists()) {
                System.out.println("El directori \""+dir.getName()+"\" no existeix");
            }else{
                File f = new File(filePath);
                if (!f.exists()) {
                    System.out.println("L'arxiu \""+f.getName()+"\" no existeix");
                }else{
                    // Si existeix afegim les dades
                    FileWriter fw = new FileWriter(filePath, true);
                    fw.write("\nHem afegit més frases a Matrix");
                    fw.close();
                    
                    llegirArxiu(f);
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }
    
}
