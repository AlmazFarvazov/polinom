package ru.kpfu.itis.j903;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Randomize randomize = new Randomize();
        randomize.random(100, "Test/input.txt");
        File input = new File("Test/input.txt");
        Scanner sc = new Scanner(input);
        ArrayList<Polinom> arrayList = new ArrayList<>();
        int deg;
        int value;
        for (int i = 0; i < 2; i++) {
            Map<Integer, Integer> pol = new MySortedMap<>();
            do {
                sc.nextLine();
                value = sc.nextInt();
                deg = sc.nextInt();
                if(pol.containsKey(deg)){
                    value += pol.get(deg);
                }
                pol.put(deg, value);
            } while (!sc.hasNext("-1"));
            System.out.println(pol);
            arrayList.add(new Polinom(pol));
        }
        System.out.println(arrayList.toString());
    }
}
