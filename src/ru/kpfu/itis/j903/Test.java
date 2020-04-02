package ru.kpfu.itis.j903;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner scs = new Scanner(System.in);
        int number = scs.nextInt();
        Randomize randomize = new Randomize();
        randomize.random(number, "Test/input.txt");
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
            System.out.println(p);
            list.add(p);
        }

        cleaning("insert.txt", number);
        cleaning("insertContains.txt", number);
        cleaning("delete.txt", number);
        cleaning("derivate.txt", number);
        cleaning("sum.txt", number);
        cleaning("toString.txt", number);
        cleaning("value.txt", number);
        cleaning("deleteOdd.txt", number);

        long startTime;
        long finishTime;
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            int d = degArrayList.get(i).get(random.nextInt(degArrayList.get(i).size()));
            int k = random.nextInt(100);
            startTime = System.nanoTime();
            p.insert(d, k);
            finishTime = System.nanoTime();
            write(finishTime-startTime, "insertContains.txt", number);
            System.out.println(finishTime - startTime);
        }
        System.out.println("///////////////////////////////////////////////////////////////");
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            int d = random.nextInt(100);
            int k = random.nextInt(100);
            while (degArrayList.get(i).contains(d)) {
                d = random.nextInt((100));
            }
            startTime = System.nanoTime();
            p.insert(d, k);
            finishTime = System.nanoTime();
            write(finishTime-startTime, "insert.txt", number);
            System.out.println(finishTime - startTime);
        }
        System.out.println("//////////////////////////////////////////////////////////////////////////////");

        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            int d = degArrayList.get(i).get(random.nextInt(degArrayList.get(i).size()));
            startTime = System.nanoTime();
            p.delete(d);
            finishTime = System.nanoTime();
            write(finishTime-startTime, "delete.txt", number);
            System.out.println(finishTime - startTime);
        }
        System.out.println("//////////////////////////////////////////////////////////");
        Polinom randomPol = list.get(random.nextInt(number));
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            startTime = System.nanoTime();
            p.sum(randomPol);
            finishTime = System.nanoTime();
            write(finishTime-startTime, "sum.txt", number);
            System.out.println(finishTime - startTime);
        }

        System.out.println("////////////////////////////////////////////////////////////////////");
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            startTime = System.nanoTime();
            p.deleteOdd();
            finishTime = System.nanoTime();
            write(finishTime-startTime, "deleteOdd.txt", number);
            System.out.println(finishTime - startTime);
        }

        System.out.println("////////////////////////////////////////////////////////////////////");
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            startTime = System.nanoTime();
            p.value(78);
            finishTime = System.nanoTime();
            write(finishTime-startTime, "value.txt", number);
            System.out.println(finishTime - startTime);
        }

        System.out.println("////////////////////////////////////////////////////////////////////");
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            startTime = System.nanoTime();
            p.toString();
            finishTime = System.nanoTime();
            write(finishTime-startTime, "toString.txt", number);
            System.out.println(finishTime - startTime);
        }


        System.out.println("////////////////////////////////////////////////////////////////////");
        for (int i = 0; i < number; i++) {
            Polinom p = list.get(i);
            startTime = System.nanoTime();
            p.derivate();
            finishTime = System.nanoTime();
            write(finishTime-startTime, "derivate.txt", number);
            System.out.println(finishTime - startTime);
        }
    }

    private static void write(long time, String path, int number) throws IOException {
        String s = "Test/" + number + "/" + path;
        String t = "" + time;
        try(FileWriter fl = new FileWriter(s, true)) {
            fl.append(t + '\n');
        }
    }

    private static void cleaning(String path, int number) throws IOException {
        String s = "Test/" + number + "/" + path;
        try(FileWriter fl = new FileWriter(s)) {
            fl.write("");
        }
    }

}
