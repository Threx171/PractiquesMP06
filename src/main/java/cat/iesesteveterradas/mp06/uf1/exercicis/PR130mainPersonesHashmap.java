package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;


public class PR130mainPersonesHashmap {
    private HashMap<String, Integer> persones = new HashMap<>();
    private String basePath = System.getProperty("user.dir") + "/data/";
    private String filePath = basePath + "PR130persones.dat";

    public static void main(String[] args) {
        PR130mainPersonesHashmap pers = new PR130mainPersonesHashmap();
        pers.persones.put("Paco", 50);
        pers.persones.put("Maria", 34);
        pers.persones.put("Julian", 24);
        pers.persones.put("Sara", 19);
        pers.persones.put("Pedro", 28);

        pers.writePerson();
        pers.readPerson();

    }
    public void writePerson() {
        try {
            File dir = new File(basePath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    System.out.println("Error en la creación de la carpeta 'data'");
                }
            }
            System.out.println("");
            FileOutputStream fos = new FileOutputStream(filePath);
            DataOutputStream dos = new DataOutputStream(fos);

            for(Iterator i = this.persones.keySet().iterator(); i.hasNext();) {
                String k = (String)i.next();
                int v = this.persones.get(k);
                dos.writeUTF(k);
                dos.writeInt(v);
            }
            System.out.println("Personas registradas correctamente\n");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readPerson() {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            DataInputStream dis = new DataInputStream(fis);

            System.out.println("Leyendo PR130Persones.dat");
            System.out.println("*************************");
            for (int i = 0; i < this.persones.size(); i++) {
                String persName = dis.readUTF();
                int persAge = dis.readInt();
                System.out.println(persName + " " + persAge);
            }
            System.out.println("*************************");
            fis.close();
            dis.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
