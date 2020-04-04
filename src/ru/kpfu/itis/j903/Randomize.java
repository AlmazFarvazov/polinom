package ru.kpfu.itis.j903;

import java.io.*;

public class Randomize {

    public static void main(String[] args) {
        Randomize rnd = new Randomize();
        rnd.random(1000,"Test/input.txt");
    }

    public void random(int number, String path) {
        File file = new File(path);
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            for (int i = 0; i < number; i++) {
                int max = (int) ( 3 + (Math.random()* + 13));
                for (int j = 0; j < max; j++) {
                    outputStreamWriter.write('\n');
                    int a = (int) (Math.random() * 200 - 100);
                    int b = (int) (Math.random() * 200);
                    outputStreamWriter.write(Integer.toString(a));
                    outputStreamWriter.write(" ");
                    outputStreamWriter.write(Integer.toString(b));
                }
                outputStreamWriter.write(" ");
                outputStreamWriter.write('\n');
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
