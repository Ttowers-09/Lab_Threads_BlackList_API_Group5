package co.eci.blacklist.labs;

/**
 * @author Group 5
 * Integrantes:
 * - Juan Sebastian Beltran Rodriguez
 * - Esteban Aguilera Contreras
 * - Carlos David Barrero Velasquez
 * - Iván Santiago Forero Torres
 */
public class CountThread extends Thread {

    //asignamos los dos valores de rango
    private final int initialNumber;
    private final int finalNumber;

    /**
     * Constructor de la clase CountThread      
     * @param name: Definimos el nombre del hilo para facilitar su identificación.
     * @param initialNumber: Definimos el número inicial del rango.
     * @param finalNumber: Definimos el número final del rango.
     */
    public CountThread(String name, int initialNumber, int finalNumber) {
        super(name);
        this.initialNumber = initialNumber;
        this.finalNumber = finalNumber;
    }

    /**
     * Método que se ejecuta al iniciar el hilo
     */
    @Override
    public void run (){
        for (int i = initialNumber; i <= finalNumber; i++) {
            System.out.print("\n" + getName() + ": " + i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Presentación primer parte del laboratorio");

        CountThread thread1 = new CountThread("th1",0, 5);
        CountThread thread2 = new CountThread("th2",5, 10);
        CountThread thread3 = new CountThread("th3",10, 15);

        // Iniciamos los hilos
        System.out.println("Iniciamos con la ejecución de los hilos (Start)");
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("\nIniciamos con la ejecución de los hilos (run)");
        thread1.run();
        thread2.run();
        thread3.run();
    }
    
}

    /**
     * Anotaciones vistas en la primer parte:
     * - Join(): El método join() en este contexto asegura que el hilo principal espere a que todos los hilos (thread1, thread2, thread3) 
     * terminen de contar e imprimir sus números antes de continuar con la ejecución o finalizar el programa. Esto ayuda a evitar que el 
     * hilo principal termine su ejecución antes de que los hilos secundarios hayan completado su tarea.
     */