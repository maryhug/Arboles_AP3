package proyectoarboles;

import java.util.Scanner;

public class Arbol {

    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    //
    public void insertarDerecho(Nodo nodo, String dato) {
        if (nodo != null) {
            Nodo nuevoNodo = new Nodo(dato);
            nodo.setLD(nuevoNodo);
            System.out.println("+" + dato);
        } else {
            System.out.println("No se puede insertar en un nodo nulo.");
        }
    }

    public void insertarIzquierdo(Nodo nodo, String dato) {
        if (nodo != null) {
            Nodo nuevoNodo = new Nodo(dato);
            nodo.setLI(nuevoNodo);
            System.out.println("-" + dato);
        } else {
            System.out.println("No se puede insertar en un nodo nulo.");
        }
    }

    public static boolean esRaiz(Nodo nodo) {
        return nodo.getLI() == null && nodo.getLD() == null;
    }

    public void crearArbol(Scanner scanner) {
        System.out.println("Ingrese el número de nodos del árbol:");
        int numNodos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese los datos de los nodos separados por espacios:");
        String[] datos = scanner.nextLine().split(" ");

        if (datos.length != numNodos) {
            System.out.println("El número de datos ingresados no coincide con el número de nodos especificado.");
            return;
        }

        raiz = new Nodo(datos[0]);
        for (int i = 1; i < numNodos; i++) {
            insertarNodoBinario(raiz, datos[i].charAt(0));
        }
    }

    public void insertarNodoBinario(Nodo nodo, char dato) {
        if (nodo == null) {
            return;
        }
        if (dato < nodo.getDato().charAt(0)) {
            if (nodo.getLI() == null) {
                nodo.setLI(new Nodo(String.valueOf(dato)));
                System.out.println("- Izquierda ->  |" + dato + "|");
            } else {
                insertarNodoBinario(nodo.getLI(), dato);
            }
        } else {
            if (nodo.getLD() == null) {
                nodo.setLD(new Nodo(String.valueOf(dato)));
                System.out.println("+ Derecha ->  |" + dato + "|");
            } else {
                insertarNodoBinario(nodo.getLD(), dato);
            }
        }
    }

    //
    public void imprimirArbol(Nodo nodo) {
        if (nodo != null) {
            System.out.println(" ");
            System.out.print(nodo.getDato() + " | ");

            if (esRaiz(nodo)) {
                System.out.println("(Hoja)");
            } else {
                System.out.println();
            }
            if (nodo.getLI() != null || nodo.getLD() != null) {
                System.out.println("Nodo padre: " + nodo.getDato());
            }
            if (nodo.getLI() != null) {
                System.out.println("Nodo hijo izquierdo: " + nodo.getLI().getDato());
            }
            if (nodo.getLD() != null) {
                System.out.println("Nodo hijo derecho: " + nodo.getLD().getDato());
            }
            imprimirArbol(nodo.getLI());
            imprimirArbol(nodo.getLD());
        }
    }

    public void grafica(Nodo nodo, int nivel, boolean esIzquierdo) {
        if (nodo != null) {
            grafica(nodo.getLD(), nivel + 1, false);
            for (int i = 0; i < nivel - 1; i++) {
                System.out.print("   ");
            }
            if (nivel > 0) {
                System.out.print(esIzquierdo ? " \\" : " /");
            }
            System.out.println(" " + nodo.getDato());
            grafica(nodo.getLI(), nivel + 1, true);
        }
    }

    //
    public int mostrarhojas(Nodo nodo) {
        int cont = 0;
        if (nodo != null) {
            if (nodo.getLD() == null && nodo.getLI() == null) {
                System.out.println("Hoja: " + nodo.getDato() + " ");
                cont++;
            }
            cont += mostrarhojas(nodo.getLI());
            cont += mostrarhojas(nodo.getLD());
        }
        return cont;
    }

    public int mostrarPadres(Nodo nodo) {
        int cont = 0;
        if (nodo != null) {
            if (!esRaiz(nodo)) {
                System.out.println("Nodo padre: " + nodo.getDato());
                cont++;
            }
            cont += mostrarPadres(nodo.getLI());
            cont += mostrarPadres(nodo.getLD());
        }
        return cont;
    }

    public void mostrarHijos(Nodo nodo) {
        int total = 0;
        if (nodo != null) {
            if (nodo.getLI() != null || nodo.getLD() != null) {
                if (nodo.getLI() != null) {
                    System.out.println("Nodo hijo izquierdo: " + nodo.getLI().getDato());
                    total++;
                }
                if (nodo.getLD() != null) {
                    System.out.println("Nodo hijo derecho: " + nodo.getLD().getDato());
                    total++;
                }
            }
            mostrarHijos(nodo.getLI());
            mostrarHijos(nodo.getLD());
        }
    }

    public int nivelDato(Nodo nodo, String dato, int nivel) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.getDato().equals(dato)) {
            return nivel;
        }
        int nivelIzquierdo = nivelDato(nodo.getLI(), dato, nivel + 1);
        if (nivelIzquierdo != -1) {
            return nivelIzquierdo;
        }
        return nivelDato(nodo.getLD(), dato, nivel + 1);
    }

    public int alturaDato(Nodo nodo, String dato) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.getDato().equals(dato)) {
            return altura(nodo);
        }
        int alturaIzquierdo = alturaDato(nodo.getLI(), dato);
        if (alturaIzquierdo != -1) {
            return alturaIzquierdo;
        }
        return alturaDato(nodo.getLD(), dato);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int alturaIzquierdo = altura(nodo.getLI());
        int alturaDerecho = altura(nodo.getLD());
        return Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

    //
    public void preOrden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            preOrden(nodo.getLI());
            preOrden(nodo.getLD());
        }
    }

    public void inOrden(Nodo nodo) {
        if (nodo != null) {
            inOrden(nodo.getLI());
            System.out.print(nodo.getDato() + " ");
            inOrden(nodo.getLD());
        }
    }

    public void postOrden(Nodo nodo) {
        if (nodo != null) {
            postOrden(nodo.getLI());
            postOrden(nodo.getLD());
            System.out.print(nodo.getDato() + " ");
        }
    }

    //
    public void insertarNodo(Nodo nodo, String datoIngresar) {
        if (nodo == null) {
            System.out.println("El nodo padre no puede ser nulo.");
            return;
        }
        if (nodo.getDato().compareTo(datoIngresar) > 0) {
            if (nodo.getLI() == null) {
                insertarIzquierdo(nodo, datoIngresar);
            } else {
                insertarNodo(nodo.getLI(), datoIngresar);
            }
        } else {
            if (nodo.getLD() == null) {
                insertarDerecho(nodo, datoIngresar);
            } else {
                insertarNodo(nodo.getLD(), datoIngresar);
            }
        }
    }

    public void eliminarNodo(Nodo padre, Nodo actual, String datoEliminar) {
        if (actual == null) {
            return;
        }
        if (actual.getDato().equals(datoEliminar)) {
            if (actual.getLI() == null && actual.getLD() == null) {
                if (padre == null) {
                    raiz = null;
                } else if (padre.getLI() == actual) {
                    padre.setLI(null);
                } else {
                    padre.setLD(null);
                }
            } else if (actual.getLI() == null) {
                if (padre == null) {
                    raiz = actual.getLD();
                } else if (padre.getLI() == actual) {
                    padre.setLI(actual.getLD());
                } else {
                    padre.setLD(actual.getLD());
                }
            } else if (actual.getLD() == null) {
                if (padre == null) {
                    raiz = actual.getLI();
                } else if (padre.getLI() == actual) {
                    padre.setLI(actual.getLI());
                } else {
                    padre.setLD(actual.getLI());
                }
            } else {
                Nodo sucesor = encontrarSucesor(actual);
                eliminarNodo(actual, sucesor, sucesor.getDato());
                actual.setDato(sucesor.getDato());
            }
        } else if (actual.getDato().compareTo(datoEliminar) > 0) {
            eliminarNodo(actual, actual.getLI(), datoEliminar);
        } else {
            eliminarNodo(actual, actual.getLD(), datoEliminar);
        }
    }

    //
    private Nodo encontrarSucesor(Nodo nodo) {
        Nodo sucesor = nodo.getLD();
        while (sucesor.getLI() != null) {
            sucesor = sucesor.getLI();
        }
        return sucesor;
    }

    public void mostrarHermanos(Nodo nodo) {
        if (nodo != null && nodo != raiz) {
            Nodo padre = encontrarPadre(raiz, nodo);
            if (padre != null) {
                System.out.println("Hermanos de " + nodo.getDato() + ":");
                if (padre.getLI() != null && !padre.getLI().getDato().equals(nodo.getDato())) {
                    System.out.println(padre.getLI().getDato());
                }
                if (padre.getLD() != null && !padre.getLD().getDato().equals(nodo.getDato())) {
                    System.out.println(padre.getLD().getDato());
                }else
                System.out.println("El nodo: " + nodo.getDato() + " NO tiene Hermanos");
            }
        }
    }

    private Nodo encontrarPadre(Nodo actual, Nodo nodo) {
        if (actual == null || actual == nodo) {
            return null;
        }
        if ((actual.getLI() != null && actual.getLI() == nodo) || (actual.getLD() != null && actual.getLD() == nodo)) {
            return actual;
        }
        Nodo izquierdo = encontrarPadre(actual.getLI(), nodo);
        if (izquierdo != null) {
            return izquierdo;
        }
        return encontrarPadre(actual.getLD(), nodo);
    }

    public Nodo buscarNodo(Nodo nodo, String dato) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getDato().equals(dato)) {
            return nodo;
        }
        Nodo izquierda = buscarNodo(nodo.getLI(), dato);
        if (izquierda != null) {
            return izquierda;
        }
        return buscarNodo(nodo.getLD(), dato);
    }

    private boolean esHermano(Nodo nodo, String datoBuscado) {
        Nodo padre = encontrarPadre(raiz, buscarNodo(raiz, datoBuscado));
        return (padre != null && (padre.getLI() == nodo || padre.getLD() == nodo));
    }

    public void mostrarPrimos(Nodo nodo, String datoBuscado) {
        if (nodo != null) {
            int nivelDatoBuscado = nivelDato(raiz, datoBuscado, 0);
            boolean tienePrimos = mostrarPrimosAux(raiz, datoBuscado, nivelDatoBuscado, null);
            if (!tienePrimos) {
                System.out.println("El nodo " + datoBuscado + " no tiene primos.");
            }
        }
    }

    private boolean mostrarPrimosAux(Nodo nodo, String datoBuscado, int nivelBuscado, Nodo padre) {
        boolean tienePrimos = false;
        if (nodo != null) {
            int nivelNodo = nivelDato(raiz, nodo.getDato(), 0);
            if (nivelNodo == nivelBuscado && !nodo.getDato().equalsIgnoreCase(datoBuscado) && !esHermano(nodo, datoBuscado)) {
                System.out.println("Primo de " + datoBuscado + ": " + nodo.getDato());
                tienePrimos = true;
            }
            tienePrimos |= mostrarPrimosAux(nodo.getLI(), datoBuscado, nivelBuscado, padre);
            tienePrimos |= mostrarPrimosAux(nodo.getLD(), datoBuscado, nivelBuscado, padre);
        }
        return tienePrimos;
    }

    public void mostrarAncestros(Nodo nodo, String dato) {
        if (nodo != null) {
            if (buscarNodo(nodo, dato) != null) {
                System.out.println("Ancestros de " + dato + ":");
                mostrarAncestrosAux(raiz, dato);
            } else {
                System.out.println("El nodo con el dato '" + dato + "' no existe en el árbol.");
            }
        } else {
            System.out.println("El árbol está vacío.");
        }
    }

    private boolean mostrarAncestrosAux(Nodo nodo, String dato) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getDato().equals(dato)) {
            return true;
        }
        if (mostrarAncestrosAux(nodo.getLI(), dato) || mostrarAncestrosAux(nodo.getLD(), dato)) {
            System.out.println(nodo.getDato());
            return true;
        }
        return false;
    }
    
}
