package ru.javawebinar.basejava;

public class MainConcurrency {

    static String d1 = "dead";
    static String d2 = "lock";

    public static void main(String[] args) throws InterruptedException {
        //deadlock
        Thread thread1 = new Thread(() -> deadTest(d1, d2));
        Thread thread2 = new Thread(() -> deadTest(d2, d1));
        thread1.start();
        thread2.start();
    }

    public static void deadTest(String a, String b) {
        synchronized (a) {
            System.out.println("Thread locked a");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println("Thread locked b");
            }
        }
    }
}








