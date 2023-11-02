package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PR131mainLlegeix extends PR131hashmap{
    public static void main(String[] args) {
        PR131mainLlegeix mainLlegeix = new PR131mainLlegeix();
        mainLlegeix.readHashMap();
    }
    public void readHashMap() {
        try {
            FileInputStream fis = new FileInputStream(getFilePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            PR131hashmap hashmap = (PR131hashmap) ois.readObject();
            ois.close();
            fis.close();

            System.out.println("Leyendo archivo PR131HashMapData.ser");
            System.out.println("************************************");
            hashmap.gethMap().forEach((k,v) -> {
                System.out.println(k + " " + v);
            });
            System.out.println("************************************");
        } catch (Exception e) {e.printStackTrace();}
    }
}
