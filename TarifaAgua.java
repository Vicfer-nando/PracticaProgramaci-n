package Ejercicios;
import java.util.Scanner;
public class TarifaAgua {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String continuar;

        do {
            System.out.print("Consumo mensual en m³: ");
            double consumo = entrada.nextDouble();

            System.out.print("¿Es una persona de la tercera edad? (si/no): ");
            boolean terceraEdad = leerBooleano(entrada);

            System.out.print("¿Tiene discapacidad? (si/no): ");
            boolean discapacidad = leerBooleano(entrada);

            double porcentajeDiscapacidad = 0;
            if (discapacidad) {
                System.out.print("Porcentaje de discapacidad (%): ");
                porcentajeDiscapacidad = entrada.nextDouble();
            }

            Usuario usuario = new Usuario(consumo, terceraEdad, discapacidad, porcentajeDiscapacidad);
            double precioInicial = calcularTarifa(usuario);
            double descuento = calcularDescuento(usuario, precioInicial);

            Factura factura = new Factura(precioInicial, descuento);
            factura.mostrar();

            System.out.print("\n¿Desea calcular otra tarifa? (si/no): ");
            continuar = entrada.next();
        } while (continuar.equalsIgnoreCase("si"));

        System.out.println("¡Gracias por usar el sistema!");
        entrada.close();
    }

    public static boolean leerBooleano(Scanner entrada) {
        String respuesta;
        do {
            respuesta = entrada.next();
            if (respuesta.equalsIgnoreCase("si")) return true;
            if (respuesta.equalsIgnoreCase("no")) return false;
            System.out.print("Respuesta inválida. Intente de nuevo (si/no): ");
        } while (true);
    }

    public static double calcularTarifa(Usuario usuario) {
        if (usuario.consumo <= 15) return 3.00;
        if (usuario.consumo <= 25) return 3.00 + (usuario.consumo - 15) * 0.10;
        if (usuario.consumo <= 40) return 3.00 + (10 * 0.10) + (usuario.consumo - 25) * 0.20;
        if (usuario.consumo <= 60) return 3.00 + (10 * 0.10) + (15 * 0.20) + (usuario.consumo - 40) * 0.30;
        return 3.00 + (10 * 0.10) + (15 * 0.20) + (20 * 0.30) + (usuario.consumo - 60) * 0.35;
    }

    public static double calcularDescuento(Usuario usuario, double precioInicial) {
        double descuento = 0;
        double descuentoBase = 3.00; 

        if (usuario.terceraEdad) {
            if (usuario.consumo <= 15) {
                descuento += descuentoBase * 0.50;
            } else {
                descuento += descuentoBase * 0.30;
            }
        }

        if (usuario.tieneDiscapacidad) {
            descuento += descuentoBase * (usuario.porcentajeDiscapacidad / 100);
        }

        return descuento;
    }
}
class Usuario {
    double consumo;
    boolean terceraEdad;
    boolean tieneDiscapacidad;
    double porcentajeDiscapacidad;

    Usuario(double consumo, boolean terceraEdad, boolean tieneDiscapacidad, double porcentajeDiscapacidad) {
        this.consumo = consumo;
        this.terceraEdad = terceraEdad;
        this.tieneDiscapacidad = tieneDiscapacidad;
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }
}

class Factura {
    double precioInicial;
    double descuento;
    double servicioAgua;
    double alcantarillado;
    double basura = 0.75;
    double procesamiento = 0.50;
    double total;

    Factura(double precioInicial, double descuento) {
        this.precioInicial = precioInicial;
        this.descuento = descuento;
        this.servicioAgua = precioInicial - descuento;
        this.alcantarillado = this.servicioAgua * 0.35;
        this.total = this.servicioAgua + alcantarillado + basura + procesamiento;
    }

    void mostrar() {
        System.out.println("\nFactura:");
        System.out.printf("Precio inicial (sin descuento): $%.2f\n", precioInicial);
        System.out.printf("Descuento aplicado: $%.2f\n", descuento);
        System.out.printf("Precio con descuento: $%.2f\n", servicioAgua);
        System.out.printf("Impuesto de alcantarillado: $%.2f\n", alcantarillado);
        System.out.printf("Recolección de basura: $%.2f\n", basura);
        System.out.printf("Procesamiento de datos: $%.2f\n", procesamiento);
        System.out.printf("Total a pagar: $%.2f\n", total);
    }
}
