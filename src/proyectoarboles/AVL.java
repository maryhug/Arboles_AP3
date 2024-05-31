package proyectoarboles;

import java.util.Scanner;

public class AVL {

    private Nodo raiz;

    public AVL() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int alturaIzquierdo = altura(nodo.getLI());
        int alturaDerecho = altura(nodo.getLD());
        return Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

    public Nodo rotacioSimpleD(Nodo nodo) {
        Nodo aux = nodo.getLI();
        nodo.setLI(aux.getLD());
        aux.setLD(nodo);
        return aux;
    }

    public Nodo rotacionSimpleI(Nodo nodo) {
        Nodo aux = nodo.getLD();
        nodo.setLD(aux.getLI());
        aux.setLI(nodo);
        return aux;

    }

    public void crearArbolAVL(Scanner arbol) {
        System.out.println("Ingrese los datos de los nodos separados por espacios:");
        String[] datos = arbol.nextLine().split(" ");

        raiz = new Nodo(datos[0]);
        for (int i = 1; i < datos.length; i++) {
            raiz = insertarAVL(raiz, datos[i].charAt(0));
        }
    }

    private Nodo insertarAVL(Nodo nodo, char dato) {
        if (nodo == null) {
            return new Nodo(String.valueOf(dato));
        }
        if (dato < nodo.getDato().charAt(0)) {
            nodo.setLI(insertarAVL(nodo.getLI(), dato));
        } else {
            nodo.setLD(insertarAVL(nodo.getLD(), dato));
        }
        int balance = balance(nodo);

        if (balance > 1 && dato < nodo.getLI().getDato().charAt(0)) {
            return rotacioSimpleD(nodo);
        }
        if (balance < -1 && dato > nodo.getLD().getDato().charAt(0)) {
            return rotacionSimpleI(nodo);
        }
        if (balance > 1 && dato > nodo.getLI().getDato().charAt(0)) {
            nodo.setLI(rotacionSimpleI(nodo.getLI()));
            return rotacioSimpleD(nodo);
        }
        if (balance < -1 && dato < nodo.getLD().getDato().charAt(0)) {
            nodo.setLD(rotacioSimpleD(nodo.getLD()));
            return rotacionSimpleI(nodo);
        }
        return nodo;
    }

    private int balance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.getLI()) - altura(nodo.getLD());
    }
    
    public void mostrarArbol() {
        mostrarArbol(raiz, "", true);
    }

    private void mostrarArbol(Nodo nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "|--- " : "|--- ") + nodo.getDato());
            mostrarArbol(nodo.getLI(), prefijo + (esUltimo ? "    " : "|   "), false);
            mostrarArbol(nodo.getLD(), prefijo + (esUltimo ? "    " : "|   "), true);
        }
    }

}