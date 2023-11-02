package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PR134randomAccessFile {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("./data/estudiants.dat", "rw");

            // Funcions disponibles
            // Afegir un nou estudiant
            afegirEstudiant(raf, 1, "Alice", 9.5f);
            afegirEstudiant(raf, 2, "Bob", 8.2f);

            // Actualitzar la nota d'un estudiant
            actualitzarNota(raf, 1, 8.0f);

            // Consultar la nota d'un estudiant
            float nota = consultarNota(raf, 2);
            if (nota != -1) {
                System.out.println("La nota de l'estudiant 2 és: " + nota);
            } else {
                System.out.println("L'estudiant no existeix.");
            }

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void afegirEstudiant(RandomAccessFile raf, int numRegistre, String nom, float nota) throws IOException {
        raf.seek(raf.length());
        raf.writeInt(numRegistre);
        escriureNomDeLongitudFixa(raf, nom, 20);
        raf.writeFloat(nota);
    }

    public static void actualitzarNota(RandomAccessFile raf, int numRegistre, float novaNota) throws IOException {
        long posicion = buscarPosicioEstudiant(raf, numRegistre);
        if (posicion != -1) {
            raf.seek(posicion + 24); // Saltem 24 bytes per arribar a la posició de la nota.
            raf.writeFloat(novaNota);
        } else {
            System.out.println("L'estudiant no existeix.");
        }
    }

    public static float consultarNota(RandomAccessFile raf, int numRegistre) throws IOException {
        long posicion = buscarPosicioEstudiant(raf, numRegistre);
        if (posicion != -1) {
            raf.seek(posicion + 24); // Saltem 24 bytes per arribar a la posició de la nota.
            return raf.readFloat();
        } else {
            return -1; // Retorna -1 si l'estudiant no existeix.
        }
    }

    private static long buscarPosicioEstudiant(RandomAccessFile raf, int numRegistre) throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            int registreActual = raf.readInt();
            if (registreActual == numRegistre) {
                return raf.getFilePointer() - 4; // Restem 4 perquè estem a la posició del número de registre.
            }
            raf.skipBytes(24); // Saltem 24 bytes (nom i nota) per anar al següent registre.
        }
        return -1; // Retorna -1 si l'estudiant no es troba.
    }

    private static void escriureNomDeLongitudFixa(RandomAccessFile raf, String nom, int longitud) throws IOException {
        if (nom.length() > longitud) {
            nom = nom.substring(0, longitud);
        } else {
            while (nom.length() < longitud) {
                nom += " "; // Omplim amb espais en blanc per assolir la longitud fixa.
            }
        }
        raf.writeBytes(nom);
    }
}
