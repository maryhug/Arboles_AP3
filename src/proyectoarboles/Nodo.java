package proyectoarboles;

public class Nodo {

    Nodo LD;
    Nodo LI;
    String Dato;

    public Nodo(String d) {
        this.LD = null;
        this.LI = null;
        this.Dato = d;
    }

    public Nodo() {
        this.LD = null;
        this.LI = null;
        this.Dato = "";
    }

    public Nodo getLD() {
        return LD;
    }

    public void setLD(Nodo LD) {
        this.LD = LD;
    }

    public Nodo getLI() {
        return LI;
    }

    public void setLI(Nodo LI) {
        this.LI = LI;
    }

    public String getDato() {
        return Dato;
    }

    public void setDato(String Dato) {
        this.Dato = Dato;
    }

}
