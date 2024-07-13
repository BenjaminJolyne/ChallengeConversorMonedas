package PRINCIPALES;

import CONEXIONAPI.ConexionAPI;
import MODELOS.Dinero;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuConvertidor {


    //Metodo para leer por teclado
    private static String input() {
        Scanner input = new Scanner(System.in);
        String x = input.nextLine();
        return x;
    }

    //Metodo para desplegar el menu de opciones de manera estatica
    public static void deployMenu(List<Dinero> dineros) {

        //HashMap para guardar el orden de las opciones de la nueva lista
        HashMap<Integer, String> mapMenu = new HashMap<>();

        int j = 1, x = 0;

        System.out.println("Bienvenido al Convertidor De Monedas!!");
        System.out.println("Disfrute su estadía");

        do {

            x = 0;
            j = 1;

            System.out.println("***************************************");
            System.out.println("Seleccione su moneda");

            //Creando la lista de opciones
            for (int i = 0; i < dineros.size(); i++) {

                System.out.println(j + ") " + dineros.get(i).getNombre() + " [" + dineros.get(i).getBase_code() + "]");
                mapMenu.put(j, dineros.get(i).getBase_code());
                j++;
            }

            System.out.println((mapMenu.size() + 1) + ") Salir");
            System.out.print("Digite la opcion: ");
            String opcion = input();
            System.out.println("***************************************");

            System.out.println("");

            //Recorriendo el hashMap en busca de la opcion digitada
            for (int i = 1; i <= mapMenu.size(); i++) {

                if (opcion.trim().equals(String.valueOf(i))) {

                    deploySubMenu(mapMenu.get(i), dineros);
                    waitKey();
                    i = mapMenu.size() + 1;

                } else if (opcion.trim().equals(String.valueOf(mapMenu.size() + 1))) {

                    System.out.println("Ejecucion finalizada");
                    x = 1;
                    i = mapMenu.size() + 1;

                } else if (i == mapMenu.size()) {

                    System.out.println("Opcion no valida, vuelva a intentar");
                    System.out.println("");
                }
            }

        } while(x == 0);
    }

    //Metodo para desplegar el sub menu de opciones
    private static void deploySubMenu(String iso, List<Dinero> dineros) {

        //HashMap para guardar el orden de las opciones de la nueva lista
        HashMap<Integer, String> mapSubMenu = new HashMap<>();

        int j = 1, x = 0;

        do {

            x = 0;
            j = 1;

            System.out.println("Selecione la moneda con la que desea");
            System.out.println("hacer el cambio");

            //Ignorando la moneda seleccionada en el menu anterior
            //Creando la lista de opciones
            for (int i = 0; i < dineros.size(); i++) {

                if(!(dineros.get(i).getBase_code().equals(iso))) {

                    System.out.println(j + ") " + dineros.get(i).getNombre() + " [" + dineros.get(i).getBase_code() + "]");
                    mapSubMenu.put(j, dineros.get(i).getBase_code());
                    j++;
                }
            }

            System.out.print("Digite la opcion: ");
            String opcion = input();

            System.out.println("");

            //Recorriendo el hashMap en busca de la opcion digitada
            for (int i = 1; i <= mapSubMenu.size(); i++) {

                if (opcion.trim().equals(String.valueOf(i))) {

                    System.out.println(ConexionAPI.response(iso, mapSubMenu.get(i), enterValue(iso, mapSubMenu.get(i))));
                    break;

                } else if (i == mapSubMenu.size()) {

                    System.out.println("Opcion no válida, por favor, vuelva a intentar");
                    System.out.println("");
                    x = 1;
                }
            }

        } while(x != 0);

        System.out.println("**************************************");
    }

    //Metodo para pedir el valor a cambiar
    private static double enterValue(String dinerosOrigen, String dineros) {

        while(true) {

            try {

                System.out.print("Digite el monto de " + dinerosOrigen + " a cambiar a " + dineros + ": $");
                String value = input();

                return Double.valueOf(value);

            } catch (Exception e) {

                System.out.println("");
                System.out.println("El monto debe ser un número, por favor, vuelva a intentarlo");
                System.out.println("");
            }
        }
    }

    //Metodo para pausar la ejecucion momentaneamente
    private static void waitKey() {

        System.out.println("");
        System.out.println("Presione cualquier tecla para continuar");
        String x = input();
    }
}
