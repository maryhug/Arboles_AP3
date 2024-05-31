package proyectoarboles;

import java.util.Scanner;

// D B F A C E G
// M J S D K Q Z A H T B

public class MenuArbol {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arbol arbol = new Arbol();
        AVL avl = new AVL();
        int opcion;
        do {
            System.out.println("===== MENÚ =====");
            System.out.println("1. Ingresar Árbol");
            System.out.println("2. Ingresar Nodo");
            System.out.println("3. Eliminar Nodo");
            System.out.println("4. Imprimir árbol (Gráfico)");
            System.out.println("5. Mostrar Recorrido");
            System.out.println("6. Mostrar solo Padres");
            System.out.println("7. Mostrar solo Hojas");
            System.out.println("8. Mostrar el nivel de un dato ingresado");
            System.out.println("9. Mostrar la altura de un dato ingresado");
            System.out.println("10. Mostrar Hermanos de un dato");
            System.out.println("11. Mostrar Primos de un dato");
            System.out.println("12. Mostrar Ancestros de un dato");
            System.out.println("13. Arbol creado AVL");
            System.out.println("0. Salir");
            System.out.println("====================");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingresar Árbol:");
                    arbol.crearArbol(scanner);

                    break;
                case 2:
                    System.out.println(" ");
                    System.out.println("Ingresar Nodo:");
                    System.out.print("Ingrese el dato del nuevo nodo: ");
                    String datoNuevo = scanner.nextLine();
                    arbol.insertarNodo(arbol.getRaiz(), datoNuevo);
                    System.out.println("Árbol actualizado:");
                    arbol.imprimirArbol(arbol.getRaiz());
                    System.out.println(" ");
                    break;
                case 3:
                    System.out.println(" ");
                    System.out.println("Eliminar Nodo");
                    System.out.println("================");
                    System.out.print("Ingrese el dato del nodo a eliminar: ");
                    String datoEliminar = scanner.nextLine();
                    arbol.eliminarNodo(null, arbol.getRaiz(), datoEliminar);
                    System.out.println("Árbol actualizado:");
                    arbol.imprimirArbol(arbol.getRaiz());
                    System.out.println(" ");
                    break;
                case 4:
                    System.out.println(" ");
                    System.out.println("Árbol Gráfico:");
                    arbol.grafica(arbol.getRaiz(), 0, false);
                    System.out.println(" ");
                    break;
                case 5:
                    int recorrido;
                    do {
                        System.out.println(" ");
                        System.out.println("====== RECORIDO ======");
                        System.out.println("1. Pre-Orden");
                        System.out.println("2. In-Orden");
                        System.out.println("3. Post-Orden");
                        System.out.println("0. Salir del Recorrido");
                        System.out.print("Ingrese opcion: ");
                        recorrido = scanner.nextInt();
                        switch (recorrido) {
                            case 1:
                                System.out.println(" ");
                                System.out.println("Recorrido en Pre-Orden:");
                                arbol.preOrden(arbol.getRaiz());
                                System.out.println(" ");
                                System.out.println(" ");
                                break;
                            case 2:
                                System.out.println(" ");
                                System.out.println("Recorrido en In-Orden:");
                                arbol.inOrden(arbol.getRaiz());
                                System.out.println(" ");
                                System.out.println(" ");
                                break;
                            case 3:
                                System.out.println(" ");
                                System.out.println("Recorrido en Post-Orden:");
                                arbol.postOrden(arbol.getRaiz());
                                System.out.println(" ");
                                System.out.println(" ");
                                break;
                            case 0:
                                System.out.println("Saliendo del Recorrido");
                                System.out.println(" ");
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                        }

                    } while (recorrido != 0);
                    break;
                case 6:
                    System.out.println(" ");
                    System.out.println("Mostrar solo Padres:");
                    int totalPadres = arbol.mostrarPadres(arbol.getRaiz());
                    System.out.println("Total de nodos padres: " + totalPadres);
                    System.out.println(" ");
                    break;
                case 7:
                    System.out.println(" ");
                    System.out.println("Mostrar solo Hojas:");
                    int totalHojas = arbol.mostrarhojas(arbol.getRaiz());
                    System.out.println("Total de nodos hojas: " + totalHojas);
                    System.out.println(" ");
                    break;
                case 8:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato del nodo para verificar su nivel: ");
                    String datoNivel = scanner.nextLine();
                    int nivel = arbol.nivelDato(arbol.getRaiz(), datoNivel, 0);
                    if (nivel != -1) {
                        System.out.println("El nivel del dato '" + datoNivel + "' es: " + nivel);
                    } else {
                        System.out.println("El dato '" + datoNivel + "' no se encuentra en el árbol.");
                    }
                    System.out.println(" ");
                    break;
                case 9:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato del nodo para verificar su altura: ");
                    String datoAltura = scanner.nextLine();
                    int altura = arbol.alturaDato(arbol.getRaiz(), datoAltura);
                    if (altura != -1) {
                        System.out.println("La altura del subárbol con raíz en '" + datoAltura + "' es: " + altura);
                    } else {
                        System.out.println("El dato '" + datoAltura + "' no se encuentra en el árbol.");
                    }
                    System.out.println(" ");
                    break;
                case 10:
                    System.out.println(" ");
                    System.out.println("Ingrese el dato del nodo del cual desea mostrar los hermanos:");
                    String datoNodo = scanner.nextLine();
                    Nodo nodoBuscar = arbol.buscarNodo(arbol.getRaiz(), datoNodo);
                    if (nodoBuscar != null) {
                        arbol.mostrarHermanos(nodoBuscar);
                    } else {
                        System.out.println("El nodo con el dato " + datoNodo + " no se encontró en el árbol.");
                    }
                    System.out.println(" ");
                    break;
                case 11:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato para el cual desea mostrar los primos: ");
                    String datoPrimos = scanner.next();
                    arbol.mostrarPrimos(arbol.getRaiz(), datoPrimos);
                    System.out.println(" ");
                    break;
                case 12:
                    System.out.println(" ");
                    System.out.print("Ingrese el dato del nodo para encontrar sus ancestros: ");
                    String datoAncestros = scanner.nextLine();
                    arbol.mostrarAncestros(arbol.getRaiz(), datoAncestros);
                    System.out.println(" ");
                    break;
                case 13:
                    avl.crearArbolAVL(scanner);
                    avl.mostrarArbol();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                  
            }
        } while (opcion != 0);
        scanner.close();
    }
}
