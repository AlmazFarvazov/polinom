package ru.kpfu.itis.j903;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TestСommon {
    public static void main(String[] args) throws IOException {
        Scanner scs = new Scanner(System.in);
        int number = scs.nextInt();
//        Randomize randomize = new Randomize();
//        randomize.random(number, "Test/input.txt");
        FileReader input = new FileReader("Test/input.txt");
        Scanner sc = new Scanner(input);
        System.out.println(sc.nextLine());
        String[] s;
        ArrayList<ArrayList<Integer>> degArrayList = new ArrayList<ArrayList<Integer>>();
        List<Polinom> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Map<Integer, Integer> map = new MySortedMap<>();
            degArrayList.add(new ArrayList<Integer>());
            while (sc.hasNextLine() && (s = sc.nextLine().split(" ")).length == 2) {
                int deg = Integer.parseInt(s[1]);
                degArrayList.get(i).add(deg);
                int koef = Integer.parseInt(s[0]);
                if (map.containsKey(deg)) koef += map.get(deg);
                map.put(deg, koef);
            }
            Polinom p = new Polinom(map);
//            System.out.println(p);
            list.add(p);
        }

        long startTime;
        long finishTime;
        long time;
        Random random = new Random();

        startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            int d = random.nextInt(100);
            int k = random.nextInt(100);
            p.insert(d, k);
        }
        time = System.nanoTime() - startTime;
        System.out.println(time);


        startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            int d = random.nextInt(100);
            p.delete(d);
        }
        time = System.nanoTime() - startTime;
        System.out.println(time);

        Polinom randomPol = list.get(random.nextInt(number));
        startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            p.sum(randomPol);
        }
        time = System.nanoTime() - startTime;
        System.out.println(time);

        startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            p.deleteOdd();
        }
        time = System.nanoTime() - startTime;
        System.out.println(time);

        startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            p.value(78);
        }
        time = System.nanoTime() - startTime;
        System.out.println(time);

//        startTime = System.nanoTime();
//        for (int i = 0; i < number; i++) {
//            Polinom p = list.get(i);
//            p.toString();
//        }
//        time = System.nanoTime() - startTime;
//        System.out.println(time);

        startTime = System.nanoTime();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            p.derivate();
        }
        time = System.nanoTime() - startTime;
        System.out.println(time);
    }

}
