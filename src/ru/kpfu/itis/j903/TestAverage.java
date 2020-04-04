package ru.kpfu.itis.j903;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestAverage {
    public static final int ITERATIONS = 2000;

    public static void main(String[] args) throws FileNotFoundException {
        Polinom p;
        Random r = new Random();
        Scanner sc = new Scanner(new FileReader("Test/input.txt"));
        sc.nextLine();
        long deltaTime = 0;
        long startTime;
        long finishTime;
        long times[] = new long[ITERATIONS];
        double[] averages = new double[100];
        double[] sigmas = new double[100];
        List<Polinom> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> degArrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String s[];
            Map<Integer, Integer> map = new MySortedMap<>();
            degArrayList.add(new ArrayList<Integer>());
            while (sc.hasNextLine() && (s = sc.nextLine().split(" ")).length == 2) {
                int deg = Integer.parseInt(s[1]);
                degArrayList.get(i).add(deg);
                int koef = Integer.parseInt(s[0]);
                if (map.containsKey(deg)) koef += map.get(deg);
                map.put(deg, koef);
            }
            p = new Polinom(map);
//                        System.out.println(p);
            list.add(p);
        }
        for (int n = 10; n <= 1000; n += 10) {
            for (int j = 0; j < ITERATIONS; j++) {
                p = list.get(r.nextInt(1000));
                startTime = System.nanoTime();
                p.value(r.nextInt(100));
                finishTime = System.nanoTime();
                deltaTime = finishTime - startTime;
                times[j] = deltaTime;
            }
            Arrays.sort(times);
            int b = (int) (times.length * 0.05);
            long[] newTimes = Arrays.copyOfRange(times, b, times.length - b);
            averages[n / 10 - 1] = average(newTimes);
            sigmas[n / 10 - 1] = sigma(newTimes, averages[n / 10 - 1]);
        }
        try (PrintWriter wr = new PrintWriter("Test/result.csv")) {
            for (int i = 0; i < averages.length; i++) {
                wr.print(((i + 1) * 10) + ";");
            }
            wr.println();
            for (int i = 0; i < averages.length; i++) {
                wr.print((int) averages[i] + ";");
            }
            wr.println();
            for (int i = 0; i < sigmas.length; i++) {
                wr.print((int) sigmas[i] + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static double average(long[] longs) {
        double sum = 0;
        for (long l : longs) {
            sum += l;
        }
        sum /= longs.length;
        return sum;
    }

    private static double sigma(long[] longs, double average) {
        double sum = 0;
        for (long l : longs) {
            sum += (l - average) * (l - average);
        }
        sum /= longs.length;
        sum = Math.sqrt(sum);
        return sum;
    }

}
