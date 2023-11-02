package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;

public class PR122cat {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/myFiles/";
        // Creem tres variables amb una ruta de directori, una ruta d'un arxiu 
        // que no existeix, i una ruta d'un arxiu que existeix
        File dir = new File(basePath);
        File f1 = new File(basePath + "file1.txt");
        File f2 = new File(basePath + "renamedFile.txt");
        // Instanciem la classe per poder fer ús dels seus métodes
        PR122cat cat = new PR122cat();
        // Cridem la funció per comprovar cada cas
        cat.comprovaRutaArxiu(dir);
        cat.comprovaRutaArxiu(f1);
        cat.comprovaRutaArxiu(f2);
    }
    // Creem la funció per comprovar la ruta de l'arxiu
    public void comprovaRutaArxiu(File rutaArxiu) {
        System.out.println("************************");
        // Comprovem que l'arxiu existeix
        if (rutaArxiu.exists()) {
            // Si existeix comprovem que es tracta d'un arxiu
            boolean esArxiu = rutaArxiu.isFile();
            if (esArxiu) {
                System.out.println("La ruta \""+rutaArxiu.getName()+"\" és un arxiu");
            }else{
                // Si no és un arxiu, comprovem que es tracta d'un directori
                boolean esDirectori = rutaArxiu.isDirectory();
                if (esDirectori) {
                    System.out.println("La ruta \""+rutaArxiu.getName()+"\" NO és un arxiu, és una carpeta");
                }
            }
        }else{
            // Si l'arxiu no existeix, ho mostrem per pantalla
            System.out.println("L'arxiu \"" +rutaArxiu.getName()+"\" no existeix");
        }
        System.out.println("************************\n");
    }
}
