import java.io.Serializable;

public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    private int vida;
    private int hambre;
    private int sueño;
    private int diversion;
    private int limpieza;

    public Pet(int vida, int hambre, int sueño, int diversion, int limpieza) {
        this.vida = vida;
        this.hambre = hambre;
        this.sueño = sueño;
        this.diversion = diversion;
        this.limpieza = limpieza;
    }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getHambre() { return hambre; }
    public void setHambre(int hambre) { this.hambre = hambre; }

    public int getSueño() { return sueño; }
    public void setSueño(int sueño) { this.sueño = sueño; }

    public int getDiversion() { return diversion; }
    public void setDiversion(int diversion) { this.diversion = diversion; }

    public int getLimpieza() { return limpieza; }
    public void setLimpieza(int limpieza) { this.limpieza = limpieza; }

    @Override
    public String toString() {
        return "Mascota{" +
                "vida=" + vida +
                ", hambre=" + hambre +
                ", sueño=" + sueño +
                ", diversion=" + diversion +
                ", limpieza=" + limpieza +
                '}';
    }
}
