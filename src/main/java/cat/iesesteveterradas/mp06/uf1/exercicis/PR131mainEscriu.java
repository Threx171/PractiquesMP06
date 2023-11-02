package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PR131mainEscriu {
    public static void main(String[] args) {
        PR131mainEscriu pMainEsc = new PR131mainEscriu();
        pMainEsc.writeHashMap();
    }
    public void writeHashMap() {
        PR131hashmap hashMap = new PR131hashmap();
        hashMap.appendInfoToMap("Paco", 50);
        hashMap.appendInfoToMap("Maria", 34);

        try {
            FileOutputStream fos = new FileOutputStream(hashMap.getFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hashMap);
            oos.close();
            fos.close();
            System.out.println("Se ha escrito correctamente el HashMap en PR131HashMapData.ser");
        }catch (Exception e) {e.printStackTrace();}
    }
}
