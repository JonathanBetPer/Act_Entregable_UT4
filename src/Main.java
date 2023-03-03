import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jonathan Betancor Perdomo
 * @version 1
 * @since 03/03/2022
 * <b>Actividad Entregable UT4 - ArrayList </b>

 * <b>Ejercicio 1 </b>
 * Realiza un programa que pida la temperatura media que ha hecho en cada mes de un
 * determinado año y que muestre a continuación un diagrama de barras horizontales con esos
 * datos. Las barras del diagrama se pueden dibujar a base de asteriscos o cualquier otro
 * carácter.

 *<b>Ejercicio 2 </b>
 * Un restaurante nos ha encargado una aplicación para colocar a los clientes en sus mesas. En
 * una mesa se pueden sentar de 0 (mesa vacía) a 4 comensales (mesa llena). Cuando llega un
 * cliente se le pregunta cuántos son. De momento el programa no está preparado para colocar
 * a grupos mayores a 4, por tanto, si un cliente dice por ejemplo que son un grupo de 6, el
 * programa dará el mensaje “Lo siento, no admitimos grupos de 6, haga grupos de 4 personas
 * como máximo e intente de nuevo”. Para el grupo que llega, se busca siempre la que haya
 * hueco para todo el grupo en las mesas ocupadas. Si no existe hueco en esas mesas será
 * necesario poner una mesa adicional en el restaurante. Inicialmente, hay 5 mesas cargadas
 * con valores aleatorios entre 0 y 4. Se podrán añadir hasta 10 mesas más, una vez estén todas
 * ocupadas el dueño del restaurante informará a los clientes que no hay hueco, deben volver
 * en otro momento. Además, cada vez que se sientan nuevos clientes se debe mostrar el estado
 * de las mesas. Los grupos no se pueden romper, aunque haya huecos sueltos suficientes.

 * <b>Ejercicio 3 </b>
 * Crea un programa que simule el funcionamiento de la administración del centro en el
 * momento que se matricula alumnado. Para ello debes tener en cuenta que existirán 3 grupos:
 * a. 1ºDAW
 * b. 2ºDAW
 * c. 1ºDAM
 * En cada grupo se pueden matricular hasta 30 alumnos. Elabora un menú para la
 * administración, preguntarás si se quiere modificar el nombre de algún alumno, darle de baja
 * o darle de alta. También se podrá ver la lista del alumnado matriculado en un momento
 * determinado (la lista debe estar ordenada por orden alfabético). NOTA: Tendrás que tener en
 * cuenta sobre que grupo vas a hacer la acción
 *
 */

public class Main {
    public static void main(String[] args) {

        Scanner escaner = new Scanner(System.in);
        Random numRandom = new Random();

        byte ejercicio;

        do {

            System.out.print("Ejercicio (1, 2 o 3): ");
            ejercicio = escaner.nextByte();

            switch (ejercicio){

                //Ejercicio 1
                case 1:

                    ArrayList<Integer> temperaturas = new ArrayList<Integer>();


                    // Pedir las temperaturas de cada mes
                    for (int i = 1; i <= 12; i++) {
                        System.out.print("Introduce la temperatura media del mes " + i + ": ");
                        int temperatura = escaner.nextInt();
                        temperaturas.add(temperatura);
                    }

                    // Encontrar la temperatura máxima
                    int tempMax = Integer.MIN_VALUE;
                    for (int temperatura : temperaturas) {
                        if (temperatura > tempMax) {
                            tempMax = temperatura;
                        }
                    }

                    // Dibujar el diagrama de barras
                    for (int temperatura : temperaturas) {
                        int numAsteriscos = (int) Math.round((double) temperatura / tempMax * 40);
                        System.out.print("Mes " + (temperaturas.indexOf(temperatura) + 1) + ": ");
                        for (int i = 0; i < numAsteriscos; i++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                break;

                //Ejercicio 2
                case 2:

                    ArrayList<Integer> mesas = new ArrayList<Integer>();
                    int numMesas = 5;
                    int maxMesas = 15;
                    int clientesTotales = 0;

                    // Inicializar mesas con valores aleatorios entre 0 y 4
                    for (int i = 0; i < numMesas; i++) {
                        int ocupacion = numRandom.nextInt(5);
                        mesas.add(ocupacion);
                        clientesTotales += ocupacion;
                    }

                    while (true) {
                        System.out.println("Hay " + clientesTotales + " clientes en el restaurante.");
                        System.out.println("¿Cuántos son en el nuevo grupo? (1-4)");
                        int numClientes = escaner.nextInt();

                        if (numClientes > 4) {
                            System.out.println("Lo siento, no admitimos grupos de " + numClientes + ", haga grupos de 4 personas como máximo e intente de nuevo.");
                            continue;
                        }

                        boolean mesaEncontrada = false;

                        // Buscar una mesa con suficiente capacidad para el nuevo grupo
                        for (int i = 0; i < numMesas; i++) {
                            if (mesas.get(i) + numClientes <= 4) {
                                mesas.set(i, mesas.get(i) + numClientes);
                                mesaEncontrada = true;
                                clientesTotales += numClientes;
                                break;
                            }
                        }

                        if (!mesaEncontrada) {
                            if (numMesas >= maxMesas) {
                                System.out.println("Lo siento, no hay mesas disponibles en este momento. Por favor, vuelva en otro momento.");
                                break;
                            } else {
                                mesas.add(numClientes);
                                numMesas++;
                                clientesTotales += numClientes;
                            }
                        }

                        // Mostrar estado de las mesas
                        System.out.println("Estado de las mesas:");
                        for (int i = 0; i < numMesas; i++) {
                            System.out.println("Mesa " + (i + 1) + ": " + mesas.get(i) + " clientes");
                        }
                    }

                break;

                //Ejercicio 3
                case 3:



                break;


                default:
                System.out.println("Saliendo del programa...");

            }



        } while (ejercicio>0&&ejercicio<4);

    }
}