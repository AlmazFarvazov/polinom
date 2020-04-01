package ru.kpfu.itis.j903;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Polinom {
    private MySortedMap<Integer, Integer> monoms;

    public Polinom(String path) {
        monoms = new MySortedMap<>();
        try (FileReader reader = new FileReader(path)) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");
                int coef = Integer.parseInt(line[0]);
                int degree = Integer.parseInt(line[1]);
                if (degree < 0) throw new IllegalMonomException("Degree can't be less than 0!");
                insert(coef, degree);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insert(int coef, int degree) {
        if (monoms.containsKey(degree)) {
            monoms.put(degree, coef + monoms.get(degree));
        } else {
            monoms.put(degree, coef);
        }
        if (monoms.get(degree) == 0) {
            monoms.remove(degree);
        }
    }

    public void delete(int deg) {
        monoms.remove(deg);
    }

    public void sum(Polinom p) {
        for (int deg : monoms.keySet()) {
            insert(monoms.get(deg), deg);
        }
    }

    public void derivate() {
        monoms.remove(0);
        MySortedMap<Integer, Integer> newMonoms = new MySortedMap<>();
        for (int deg : monoms.keySet()) {
            newMonoms.put(deg - 1, monoms.get(deg) * deg);
        }
        monoms = newMonoms;
    }

    public void deleteOdd() {
        for (int deg : monoms.keySet()) {
            if (monoms.get(deg) % 2 == 1) {
                delete(deg);
            }
        }
    }

    public int value(int x) {
        ArrayList<Integer> firstRow = new ArrayList<>();
        for (int i = monoms.getFirstKey(); i >= 0; i--) {
            int c = monoms.containsKey(i) ? monoms.get(i) : 0;
            firstRow.add(c);
        }
        int res = firstRow.get(0);
        for (int i = 1; i < firstRow.size(); i++) {
            res = x * res + firstRow.get(i);
        }
        return res;
    }

    @Override
    public String toString() {
        String s = "";
        for (int key : monoms.keySet()) {
            char sign = monoms.get(key) > 0 ? '+' : '-';
            s += (sign + " " + Math.abs(monoms.get(key)) + "*x^" + key + " ");
        }
        if (s.charAt(s.length() - 2) == '0') return s.substring(2, s.length() - 5);
        return s.substring(2);
    }
}
