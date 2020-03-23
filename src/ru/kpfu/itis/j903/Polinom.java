package ru.kpfu.itis.j903;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Polinom {
    private MySortedMap<Integer, Integer> monoms;

    public static void main(String[] args) {
        Polinom p  = new Polinom("/home/almaz/IdeaProjects/Polinom/test.txt");
        System.out.println(p);
    }

    public Polinom(String path) {
        monoms = new MySortedMap<>();
        try(FileReader reader = new FileReader(path))
        {
            Scanner sc = new Scanner(reader);
            while(sc.hasNext()){
                String[] line = sc.nextLine().split(" ");
                int coef = Integer.parseInt(line[0]);
                int degree = Integer.parseInt(line[1]);
                insert(coef, degree);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insert (int coef, int degree) {
        if (monoms.containsKey(degree)) {
            monoms.put(degree, coef + monoms.get(degree));
        }
        else {
            monoms.put(degree, coef);
        }
        if (monoms.get(degree) == 0) {
            monoms.remove(degree);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int key : monoms.keySet()) {
            char sign = monoms.get(key) > 0 ? '+' : '-';
            s += (sign + " " + Math.abs(monoms.get(key)) + "*x^" + key + " ");
        }
        return s.substring(2);
    }
}
