package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.nio.file.*;
import java.io.IOException;

public class PR125cp {
    public static void main(String[] args) {
        PR125cp cp = new PR125cp();
        Path pathDirectori = Paths.get(System.getProperty("user.dir") + "/Matrix/");
        Path pathArxiu = Paths.get(System.getProperty("user.dir") + "/data/myFiles/" + "renamedFile.txt");
        cp.copiaArxiu(pathArxiu, pathDirectori);
    }
    public void copiaArxiu(Path rutaArxiu, Path rutaDestinacio) {
        try {
            // Agafem el path de l'arxiu i directori i el copiem utilitzant files
            Files.copy(rutaArxiu, rutaDestinacio.resolve(rutaArxiu.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arxiu copiat");
        } catch (IOException e) {e.printStackTrace();}
        
    }
}
