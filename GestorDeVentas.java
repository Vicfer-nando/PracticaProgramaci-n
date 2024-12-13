package Ejercicios;
import java.util.Scanner;

public class GestorVentasAutos {
    // Constantes financieras
    private static final double SALARIO_BASE_MENSUAL = 2500.00;
    private static final double COMISION_POR_AUTO = 250.00;
    private static final double VALOR_MINIMO_AUTO_COMISION = 10000.00;
    private static final double PORCENTAJE_UTILIDAD = 0.05;

    // Método principal
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String nombreempleado;
        int opcion;

        do {
            System.out.println("\n--- Sistema de Gestión de Ventas de Autos ---");
            System.out.println("1. Registrar Ventas");
            System.out.println("2. Salir");
            System.out.print("Elija una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del empleado: ");
                    nombreempleado = entrada.nextLine();
                    procesarVentas(nombreempleado);
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 2);

        entrada.close();
    }

    private static void procesarVentas(String nombreEmpleado) {
        Scanner entrada = new Scanner(System.in);
        int totalAutosVendidos = 0;
        int autosConComision = 0;
        double valorTotalVentas = 0.0;

        while (true) {
            System.out.println("\n--- Registro de Ventas ---");
            System.out.println("Ingrese el valor del automóvil");
            System.out.println("Escriba 'FIN' para terminar el registro");
            System.out.print("Valor: ");
            
            String entrada_usuario = entrada.nextLine();
            
            // Condición para finalizar
            if (entrada_usuario.equalsIgnoreCase("FIN")) {
                break;
            }
            
            // Convertir entrada a valor numérico
            double valorAuto;
            try {
                valorAuto = Double.parseDouble(entrada_usuario);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número o 'FIN'.");
                continue;
            }

            // Validaciones de valor
            if (valorAuto <= 0) {
                System.out.println("El valor del auto debe ser mayor a cero.");
                continue;
            }

            totalAutosVendidos++;

            if (valorAuto >= VALOR_MINIMO_AUTO_COMISION) {
                autosConComision++;
                valorTotalVentas += valorAuto;
                System.out.println("Auto registrado con comisión");
            } else {
                System.out.println("Auto registrado sin comisión");
            }
        }

        double comisionTotal = calcularComision(autosConComision);
        double utilidadTotal = calcularUtilidad(valorTotalVentas);
        double montoTotalPagar = calcularMontoTotal(comisionTotal, utilidadTotal);

        generarInformeFinal(
            nombreEmpleado, 
            totalAutosVendidos, 
            autosConComision, 
            valorTotalVentas, 
            comisionTotal, 
            utilidadTotal, 
            montoTotalPagar
        );
    }

    private static double calcularComision(int autosConComision) {
        return autosConComision * COMISION_POR_AUTO;
    }

    private static double calcularUtilidad(double valorTotalVentas) {
        return valorTotalVentas * PORCENTAJE_UTILIDAD;
    }

    private static double calcularMontoTotal(double comisionTotal, double utilidadTotal) {
        return SALARIO_BASE_MENSUAL + comisionTotal + utilidadTotal;
    }

    private static void generarInformeFinal(
        String nombreEmpleado,
        int totalAutosVendidos,
        int autosConComision,
        double valorTotalVentas,
        double comisionTotal,
        double utilidadTotal,
        double montoTotalPagar
    ) {
        System.out.println("\n===== INFORME DE VENTAS =====");
        System.out.println("Empleado: " + nombreEmpleado);
        System.out.println("------------------------------");
        System.out.println("Total Autos Vendidos: " + totalAutosVendidos);
        System.out.println("Autos con Comisión: " + autosConComision);
        System.out.printf("Valor Total de Ventas: $%.2f%n", valorTotalVentas);
        System.out.println("\n--- Desglose Financiero ---");
        System.out.printf("Salario Base: $%.2f%n", SALARIO_BASE_MENSUAL);
        System.out.printf("Comisión por Ventas: $%.2f%n", comisionTotal);
        System.out.printf("Utilidad Total (5%%): $%.2f%n", utilidadTotal);
        System.out.println("------------------------------");
        System.out.printf("Monto Total a Pagar: $%.2f%n", montoTotalPagar);
        System.out.println("===========================");
    }
}
