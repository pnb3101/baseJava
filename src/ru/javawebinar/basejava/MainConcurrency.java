package ru.javawebinar.basejava;

public class MainConcurrency {
    /*public static final int Thread_num = 10000;
    public static final int THREAD_NUM = Thread_num;
    private static int counter;
    private static final Object LOCK = new Object();
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();
    private static int a = 0;
    private static int b = 0;
    private static int c;
    */
    public static void main(String[] args) throws InterruptedException {
        //deadlock
        String d1 = "dead";
        String d2 = "lock";

        Thread thread1 = new Thread(() -> {
            synchronized (d1) {
                System.out.println("Thread1 locked d1");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (d2) {
                    System.out.println("Thread1 locked d2");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (d2) {
                System.out.println("Thread2 locked d2");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (d1) {
                    System.out.println("Thread2 locked d1");
                }
            }
        });
        thread1.start();
        thread2.start();
     /*   System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();
        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREAD_NUM);

        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);

        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private static synchronized void inc() {
        //         synchronized(this) {
        counter++;
    */
    }
}







