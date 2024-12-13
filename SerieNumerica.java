package Ejercicios;
import java.util.Scanner;
public class Serienumericaa {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Solicitar al usuario el número de términos de la serie
        System.out.print("Ingrese el número de términos de la serie: ");
        int cantidadTerminos = entrada.nextInt();

        // Calcular la serie y mostrar resultados
        Serie serie = new Serie();
        double suma = serie.calcularSerie(cantidadTerminos);

        System.out.printf("\nSuma de la serie: %.5f\n", suma);

        entrada.close();
    }
}

// Clase para manejar la serie completa
class Serie {
    private int numerador1 = 1;
    private int numerador2 = 1;
    private int denominador = 1;
    private int potencia = 2;
    private int signo = 1;

    // Método para calcular la serie y mostrar resultados
    public double calcularSerie(int cantidadTerminos) {
        double suma = 0;
        StringBuilder serieCompleta = new StringBuilder(); // Para almacenar la serie generada

        System.out.println("\nSerie generada:");
        for (int i = 1; i <= cantidadTerminos; i++) {
            // Calcular el numerador usando Fibonacci
            int numerador = this.numerador1;
            this.numerador1 = this.numerador2;
            this.numerador2 = numerador + this.numerador1;

            // Crear un término
            Termino termino = new Termino(numerador, this.denominador, this.potencia, this.signo);

            // Agregar el término a la serie completa
            if (i > 1) {
                serieCompleta.append((this.signo > 0) ? " + " : " - ");
            }
            serieCompleta.append(String.format("(%d / %d)^%d", termino.getNumerador(), termino.getDenominador(), termino.getPotencia()));

            // Mostrar el término
            System.out.println(termino);
            System.out.printf("Término %d: %.5f\n", i, termino.calcularValor());

            // Sumar el término
            suma += termino.calcularValor();

            // Actualizar valores
            this.denominador += 2;
            this.potencia += 2;
            this.signo = alternarSigno(i, this.signo);
        }

        // Mostrar la serie generada
        System.out.println("\nSerie completa:");
        System.out.println(serieCompleta);

        return suma;
    }

    // Método para alternar el signo según la posición
    private int alternarSigno(int posicion, int signoActual) {
        if (posicion % 4 == 0)
            return 1; // Cambiar signo después de dos negativos
        else if (posicion % 4 == 2)
            return -1; // Cambiar signo después de dos positivos
        return signoActual;
    }
}

// Clase para representar un término de la serie
class Termino {
    private int numerador;
    private int denominador;
    private int potencia;
    private int signo;

    public Termino(int numerador, int denominador, int potencia, int signo) {
        this.numerador = numerador;
        this.denominador = denominador;
        this.potencia = potencia;
        this.signo = signo;
    }

    public double calcularValor() {
        return Math.pow((double) numerador / denominador, potencia) * signo;
    }

    @Override
    public String toString() {
        return String.format("(%d / %d)^%d %s", numerador, denominador, potencia, (signo > 0) ? "+" : "-");
    }

    // Getters para acceder a los valores
    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public int getPotencia() {
        return potencia;
    }
}
