package ru.kpfu.itis.j903;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.io.File;

public class Test {
    public static void main(String[] args) throws IOException {
        FileReader input = new FileReader("/home/almaz/Рабочий стол/test.txt");
        Scanner sc = new Scanner(input);
        System.out.println(sc.nextLine());
        String[] s;
        List<Polinom> list = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            Map<Integer, Integer> map = new MySortedMap<>();
            while (sc.hasNextLine() && (s=sc.nextLine().split(" ")).length == 2){
                int deg = Integer.parseInt(s[1]);
                int koef = Integer.parseInt(s[0]);
                if (map.containsKey(deg)) koef += map.get(deg);
                map.put(deg, koef);
            }
            Polinom p = new Polinom(map);
            System.out.println(p);
            list.add(p);
        }
        System.out.println(list.size());
    }
}
