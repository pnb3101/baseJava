package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainConcurrency {

    static String d1 = "dead";
    static String d2 = "lock";
    static int[] arr = {1,2,2,3,4,5,5,5,9};

    public static void main(String[] args) throws InterruptedException {
        //deadlock
       /* Thread thread1 = new Thread(() -> deadTest(d1, d2));
        Thread thread2 = new Thread(() -> deadTest(d2, d1));
        thread1.start();
        thread2.start();*/
        System.out.println(minValue(arr));
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(4);
        System.out.println(oddOrEven(list));
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
    /*
    реализовать метод через стрим int minValue(int[] values).
    Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
    составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
    Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
     */

    public static int minValue(int[] values){
      //  Stream<Integer> stream = (Stream<Integer>) Stream.of(values);
        int num = 0;
        int circle = 0;
        int[] result = IntStream.of(values).distinct().sorted().toArray();
        for(int i = result.length-1; i>=0; i--){
                num = num + result[circle] * ((int) Math.pow(10, i));
                circle++;
        }
        return num;
    }
    /*
    реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все нечетные,
     если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим
     */
    public static List<Integer> oddOrEven(List<Integer> integers){
        return integers.stream().reduce(0, (a, b) -> a + b)%2 == 0 ?
                integers.stream().filter(p->p%2!=0).collect(Collectors.toList()) : integers.stream().filter(p->p%2==0).collect(Collectors.toList());

    }

}








