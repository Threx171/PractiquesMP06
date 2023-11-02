package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.Serializable;

public class PR132persona implements Serializable {
    private String nom;
    private String cognom;
    private int edat;

    public PR132persona(String nom, String cognom, int edat) {
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
    }

    @Override
    public String toString() {
        return "Nom: "+nom + " Cognom: "+cognom+ " Edat: "+edat;
    }
}
