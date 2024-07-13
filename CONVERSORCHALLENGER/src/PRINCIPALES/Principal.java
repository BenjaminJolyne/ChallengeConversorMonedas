package PRINCIPALES;

import MODELOS.Dinero;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        List<Dinero> dineros = new ArrayList<>();

        dineros.add(new Dinero("USD", "Dolar Americano"));
        dineros.add(new Dinero("ARS", "Peso Argentino"));
        dineros.add(new Dinero("COP", "Peso Colombiano"));
        dineros.add(new Dinero("CLP", "Peso Chileno"));
        dineros.add(new Dinero("BRL", "Real Brasileño"));
        dineros.add(new Dinero("VES", "Bolivar Venezolano"));

        try {

            MenuConvertidor.deployMenu(dineros);

        } catch (Exception e) {

            System.out.println("Error: " + e);
        }
    }
}
