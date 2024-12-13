package Ejercicios;
import java.util.Scanner;
public class LlamadasInternacionales {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Solicitar la zona de destino
        System.out.print("Ingrese la zona de destino (America del norte, America central, America del sur, Europa, Asia, Africa, Oceania o Resto del mundo): ");
        String zonaInput = scanner.nextLine().toUpperCase().replace(" ", "_");
        Zona zonaDestino = Zona.valueOf(zonaInput);

        // Solicitar la cantidad de minutos
        System.out.print("Ingrese la cantidad de minutos de la llamada: ");
        int minutos = scanner.nextInt();

        // Calcular el costo total de la llamada
        double costoTotal = calcularCostoLlamada(zonaDestino, minutos);

        // Mostrar el costo total
        System.out.printf("El costo total de la llamada de %d minutos es de: $%.2f%n", minutos, costoTotal);

        // Cerrar el scanner
        scanner.close();
    }

    private static double calcularCostoLlamada(Zona zona, int minutos) {
        double precioPorMinuto = obtenerPrecioPorMinuto(zona);
        return precioPorMinuto * minutos;
    }

    private static double obtenerPrecioPorMinuto(Zona zona) {
        switch (zona) {
            case AMERICA_DEL_NORTE:
                return 2.75;
            case AMERICA_CENTRAL:
                return 1.89;
            case AMERICA_DEL_SUR:
                return 1.60;
            case EUROPA:
                return 3.50;
            case ASIA:
                return 4.50;
            case AFRICA:
                return 3.10;
            case OCEANIA:
                return 3.00;
            case RESTO_DEL_MUNDO:
                return 6.00;
            default:
                throw new IllegalArgumentException("Zona de destino no válida");
        }
    }

    private enum Zona {
        AMERICA_DEL_NORTE,
        AMERICA_CENTRAL,
        AMERICA_DEL_SUR,
        EUROPA,
        ASIA,
        AFRICA,
        OCEANIA,
        RESTO_DEL_MUNDO
    }
}
