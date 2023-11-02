package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.*;

public class PR132main {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR132people.dat";

        PR132persona pers1 = new PR132persona("Maria", "López", 36);
        PR132persona pers2 = new PR132persona("Gustavo", "Ponts", 63);
        PR132persona pers3 = new PR132persona("Irene", "Sales", 54);

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(pers1);
            oos.writeObject(pers2);
            oos.writeObject(pers3);

            oos.close();
            fos.close();
            System.out.println("Personas correctamente grabadas");

            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            PR132persona p1 = (PR132persona) ois.readObject();
            PR132persona p2 = (PR132persona) ois.readObject();
            PR132persona p3 = (PR132persona) ois.readObject();

            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);

            ois.close();
            fis.close();

        }catch (Exception e) {e.printStackTrace();}
    }
}
