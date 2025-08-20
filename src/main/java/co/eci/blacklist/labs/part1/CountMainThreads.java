package co.eci.blacklist.labs.part1;

public class CountMainThreads {
  public static void main(String[] args) throws InterruptedException {
    CountThread t1 = new CountThread(0, 99);
    CountThread t2 = new CountThread(99, 199);
    CountThread t3 = new CountThread(200, 299);

    // Version con start(): permite hilos concurrentes (descomentar para usar)
    // t1.start();
    // t2.start();
    // t3.start();

    // Version con run(): ejecuta secuencialmente en el hilo principal
    t1.run();
    t2.run();
    t3.run();

    // Si usas start(), utiliza join() para esperar a que los hilos finalicen
    // t1.join();
    // t2.join();
    // t3.join();
  }
}