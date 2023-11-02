package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.File;
import java.io.IOException;

public class PR121Files {

    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/myFiles/";

        // Crear la carpeta 'myFiles' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'myFiles'");
            }
        }
        try {
            // Crear els dos arxius si no existeixen
            File f1 = new File(basePath + "file1.txt");
            File f2 = new File(basePath + "file2.txt");
            boolean arxiu1Creat = f1.createNewFile();
            boolean arxiu2Creat = f2.createNewFile();
            if (arxiu1Creat && arxiu2Creat) {
                System.out.println("S'han creat els arxius \""+f1.getName()+", "+f2.getName()+"\"");
            } else {
                System.out.println("No s'han pogut crear els arxius \""+f1.getName()+", "+f2.getName()+"\"");
            }
            // Renombrar l'arxiu file2.txt
            f2.renameTo(new File(basePath + "renamedFile.txt"));
            System.out.println("S'ha renombrat l'arxiu file2.txt a renamedFile.txt");
            // Mostrar els arxius de la carpeta 'myFiles'
            String llistaArxius = "Els arxius de la carpeta són: ";
            for (File file : dir.listFiles()) {
                llistaArxius += file.getName()+ " ";
            }
            System.out.println(llistaArxius);
            // Eliminar l'arxiu file1.txt
            f1.delete();
            System.out.println("S'ha eliminat l'arxiu file1.txt");
            llistaArxius = "Els arxius de la carpeta, després d'esborrar file1.txt, són: ";
            for (File file : dir.listFiles()) {
                llistaArxius += file.getName()+ " ";
            }
            System.out.println(llistaArxius);

        }catch (IOException e) {e.printStackTrace();}
    }
}
