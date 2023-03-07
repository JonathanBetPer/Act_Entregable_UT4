import java.util.*;

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

            try {
                ejercicio = escaner.nextByte();
            }catch (InputMismatchException e){
                escaner.nextLine();
                ejercicio=0;
            }

            switch (ejercicio){

                //Ejercicio 1
                case 1:
                    System.out.println("");

                    ArrayList<Integer> temperaturas = new ArrayList<Integer>();
                    boolean tempNum;
                    int temperatura=0;

                    // Pedir las temperaturas de cada mes
                    for (int i = 1; i <= 12; i++) {
                        System.out.print("Introduce la temperatura media del mes " + i + ": ");

                        do {
                            try {
                                tempNum=false;
                                temperatura = escaner.nextInt();
                            }catch (InputMismatchException e){
                                escaner.nextLine();
                                temperatura=0;
                                tempNum=true;
                            }
                        }while (tempNum);

                        temperaturas.add(temperatura);
                    }

                    // Encontrar la temperatura máxima
                    int tempMax = Integer.MIN_VALUE;
                    for (int temp : temperaturas) {
                        if (temp > tempMax) {
                            tempMax = temp;
                        }
                    }

                    // Dibujar el diagrama de barras
                    for (int temp : temperaturas) {
                        int numAsteriscos = (int) Math.round((double) temp / tempMax * 40);
                        System.out.print("Mes " + (temperaturas.indexOf(temp) + 1) + ": ");
                        for (int i = 0; i < numAsteriscos; i++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                break;

                //Ejercicio 2
                case 2:
                    System.out.println("");

                    ArrayList<Integer> mesas = new ArrayList<Integer>();
                    int numMesas = 5, ocupacion=0, numClientes=0;
                    boolean mesaEncontrada=false, maxMesas=true;


                    // Inicializar mesas con valores aleatorios entre 0 y 4
                    for (int i = 0; i < numMesas; i++) {
                        ocupacion = numRandom.nextInt(5);
                        mesas.add(ocupacion);
                    }

                    while (maxMesas) {
                        System.out.println("¿Cuántos son en el nuevo grupo? (1-4)");

                        try {
                            numClientes = escaner.nextInt();
                        } catch (InputMismatchException e) {
                            escaner.nextLine();
                            numClientes = Integer.MIN_VALUE;
                        }

                        //Posibles errores
                        if (numClientes > 4) {
                            System.out.println("Lo siento, no admitimos grupos de " + numClientes + ", haga grupos de 4 personas como máximo e intente de nuevo.");
                            continue;
                        } else if (numClientes == Integer.MIN_VALUE) {
                            System.out.println("Error: Eso no es un número");
                            continue;
                        }

                        mesaEncontrada = false;

                        // Buscar mesa para grupo
                        for (int i = 0; i < numMesas; i++) {
                            if (mesas.get(i) + numClientes <= 4) {
                                mesas.set(i, mesas.get(i) + numClientes);
                                mesaEncontrada = true;
                                break;
                            }
                        }

                        if (!mesaEncontrada) {
                            if (numMesas >= 15) {
                                System.out.println("Lo siento, no hay mesas disponibles en este momento. Por favor, vuelva en otro momento.");
                                maxMesas = false;
                            } else {
                                mesas.add(numClientes);
                                numMesas++;
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
                    System.out.println("");

                    ArrayList<String> DAW1 = new ArrayList<String>() , DAW2 = new ArrayList<String>(), DAM1 = new ArrayList<String>();
                    int opcionMenu=0, numAlumno=0;
                    String opcionGrupo="", nombreAlumno="";


                    do {

                        //Ordenar listas
                        Collections.sort(DAW1);
                        Collections.sort(DAW2);
                        Collections.sort(DAM1);

                        System.out.println("");
                        System.out.println("1. Dar de alta a un alumno");
                        System.out.println("2. Dar de baja a un alumno");
                        System.out.println("3. Ver lista de alumnos");
                        System.out.println("4. Modificar nombre de alumno");
                        System.out.println("5. Salir");
                        System.out.print("-> ");

                        try {
                            opcionMenu = escaner.nextInt();
                        }catch (InputMismatchException e){
                            opcionMenu=0;
                        }
                        escaner.nextLine();


                        switch (opcionMenu) {

                            //Añadir
                            case 1:
                                System.out.println("a) 1º DAW");
                                System.out.println("b) 2º DAW");
                                System.out.println("c) 1º DAM");
                                System.out.print("-> ");
                                opcionGrupo=escaner.nextLine().toLowerCase(Locale.ROOT).trim();


                                switch (opcionGrupo){
                                    case "a":
                                        if (DAW1.size()<30) {
                                            System.out.print("Introduzca nombre del alumno a añadir: ");
                                            DAW1.add(escaner.nextLine());
                                        } else {
                                            System.out.println("1º DAW no acepta más alumnos");
                                        }
                                    break;

                                    case "b":
                                        if (DAW2.size()<30) {
                                            System.out.print("Introduzca nombre del alumno a añadir: ");
                                            DAW2.add(escaner.nextLine());
                                        } else {
                                            System.out.println("1º DAW no acepta más alumnos");
                                        }
                                    break;

                                    case "c":
                                        if (DAM1.size()<30) {
                                            System.out.print("Introduzca nombre del alumno a añadir: ");
                                            DAM1.add(escaner.nextLine());
                                        } else {
                                            System.out.println("1º DAW no acepta más alumnos");
                                        }
                                    break;

                                    default:
                                        System.out.println("Opción no válida.");
                                    break;
                                }
                            break;


                            //Elimnar
                            case 2:
                                System.out.println("a) 1º DAW");
                                System.out.println("b) 2º DAW");
                                System.out.println("c) 1º DAM");
                                System.out.print("-> ");
                                opcionGrupo=escaner.nextLine().toLowerCase(Locale.ROOT).trim();


                                switch (opcionGrupo){
                                    case "a":
                                        if (!DAW1.isEmpty()) {
                                            System.out.print("Introduzca nombre del alumno a eliminar: ");
                                            nombreAlumno=escaner.nextLine().trim();

                                            if (DAW1.contains(nombreAlumno)){
                                                DAW1.remove(nombreAlumno);
                                            } else {
                                                System.out.println("Nombre incorrecto");
                                            }

                                        } else {
                                            System.out.println("1º DAW no tiene alumnos para eliminar");
                                        }
                                        break;

                                    case "b":
                                        if (!DAW2.isEmpty()) {
                                            System.out.print("Introduzca nombre del alumno a eliminar: ");
                                            nombreAlumno=escaner.nextLine().trim();

                                            if (DAW2.contains(nombreAlumno)){
                                                DAW2.remove(nombreAlumno);
                                            } else {
                                                System.out.println("Nombre incorrecto");
                                            }

                                        } else {
                                            System.out.println("2º DAW no tiene alumnos para eliminar");
                                        }
                                    break;

                                    case "c":
                                        if (!DAM1.isEmpty()) {
                                            System.out.print("Introduzca nombre del alumno a eliminar: ");
                                            nombreAlumno=escaner.nextLine().trim();

                                            if (DAM1.contains(nombreAlumno)){
                                                DAM1.remove(nombreAlumno);
                                            } else {
                                                System.out.println("Nombre incorrecto");
                                            }

                                        } else {
                                            System.out.println("1º DAM no tiene alumnos para eliminar");
                                        }
                                    break;

                                    default:
                                        System.out.println("Opción no válida.");
                                    break;
                                }
                            break;

                            //Mostrar
                            case 3:
                                System.out.println("a) 1º DAW");
                                System.out.println("b) 2º DAW");
                                System.out.println("c) 1º DAM");
                                System.out.print("-> ");
                                opcionGrupo=escaner.nextLine().toLowerCase(Locale.ROOT).trim();

                                switch (opcionGrupo){
                                    case "a":
                                        if (!DAM1.isEmpty()){
                                            System.out.println("Lista 1º DAW");
                                            for (String alumno : DAW1) {
                                                System.out.println((DAW1.indexOf(alumno)+1) + ". " + alumno);
                                            }
                                        }else {
                                            System.out.println("Error: No hay alumnos en el grupo 1º DAW");
                                        }
                                    break;

                                    case "b":
                                        if (!DAM1.isEmpty()){
                                            System.out.println("Lista 2º DAW");
                                            for (String alumno : DAW2) {
                                                System.out.println((DAW2.indexOf(alumno)+1) + ". " + alumno);
                                            }
                                        }else {
                                            System.out.println("Error: No hay alumnos en el grupo 2º DAW");
                                        }
                                    break;

                                    case "c":
                                        if (!DAM1.isEmpty()){
                                            System.out.println("Lista 1 DAM");
                                            for (String alumno : DAM1) {
                                                System.out.println((DAM1.indexOf(alumno)+1) + ". " + alumno);
                                            }
                                        }else {
                                            System.out.println("Error: No hay alumnos en el grupo 1º DAM");
                                        }
                                    break;

                                    default:
                                        System.out.println("Opción no válida.");
                                    break;
                                }
                            break;


                            //Modificar
                            case 4:
                                System.out.println("a) 1º DAW");
                                System.out.println("b) 2º DAW");
                                System.out.println("c) 1º DAM");
                                System.out.print("-> ");
                                opcionGrupo=escaner.nextLine().toLowerCase(Locale.ROOT).trim();

                                switch (opcionGrupo){
                                    case "a":
                                        if (!DAW1.isEmpty()){
                                            System.out.println("Lista 1º DAW");
                                            for (String alumno : DAW1) {
                                                System.out.println((DAW1.indexOf(alumno)+1) + ". " + alumno);
                                            }

                                            System.out.print("\nSeleccione número de alumno a modificar: ");
                                            try {
                                                numAlumno=escaner.nextInt();
                                                escaner.nextLine();
                                            }catch (InputMismatchException e){
                                                numAlumno=0;
                                                escaner.nextLine();
                                            }

                                            if (numAlumno>0 && numAlumno<=DAW1.size()){
                                                System.out.print("Introduzca el nombre del alumno: ");
                                                DAW1.remove((numAlumno-1));
                                                DAW1.add((numAlumno-1),escaner.nextLine());
                                            }else {
                                                System.out.println("Valor erróneo");
                                            }

                                        } else {
                                            System.out.println("Error: No hay alumnos en el grupo 1º DAW");
                                        }

                                        break;

                                    case "b":
                                        if (!DAW2.isEmpty()){
                                            System.out.println("Lista 2º DAW");
                                            for (String alumno : DAW2) {
                                                System.out.println((DAW2.indexOf(alumno)+1) + ". " + alumno);
                                            }

                                            System.out.print("\nSeleccione número de alumno a modificar: ");
                                            try {
                                                numAlumno=escaner.nextInt();
                                                escaner.nextLine();
                                            }catch (InputMismatchException e){
                                                numAlumno=0;
                                                escaner.nextLine();
                                            }

                                            if (numAlumno>0 && numAlumno<=DAW2.size()){
                                                System.out.print("Introduzca el nombre del alumno: ");
                                                DAW2.remove((numAlumno-1));
                                                DAW2.add((numAlumno-1),escaner.nextLine());
                                            }else {
                                                System.out.println("Valor erróneo");
                                            }

                                        } else {
                                            System.out.println("Error: No hay alumnos en el grupo 2º DAW");
                                        }break;

                                    case "c":
                                        if (!DAM1.isEmpty()){
                                            System.out.println("Lista 1º DAW");
                                            for (String alumno : DAM1) {
                                                System.out.println((DAM1.indexOf(alumno)+1) + ". " + alumno);
                                            }

                                            System.out.print("\nSeleccione número de alumno a modificar: ");
                                            try {
                                                numAlumno=escaner.nextInt();
                                                escaner.nextLine();
                                            }catch (InputMismatchException e){
                                                numAlumno=0;
                                                escaner.nextLine();
                                            }

                                            if (numAlumno>0 && numAlumno<=DAM1.size()){
                                                System.out.print("Introduzca el nombre del alumno: ");
                                                DAM1.remove((numAlumno-1));
                                                DAM1.add((numAlumno-1),escaner.nextLine());
                                            }else {
                                                System.out.println("Valor erróneo");
                                            }

                                        } else {
                                            System.out.println("Error: No hay alumnos en el grupo 1º DAM");
                                        }
                                    break;

                                    default:
                                        System.out.println("Opción no válida.");
                                    break;
                                }
                            break;

                            case 5:
                                System.out.println("Saliendo del menú administrativo");
                            break;

                            default:
                                System.out.println("Opción no válida.");
                            break;
                        }

                    } while (opcionMenu!=6);
                break;


                default:
                    System.out.println("Saliendo del programa...");
                break;
            }



        } while (ejercicio>0&&ejercicio<4);

        escaner.close();
    }
}