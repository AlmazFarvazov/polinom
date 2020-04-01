package ru.kpfu.itis.j903;

import jdk.internal.org.objectweb.asm.tree.FrameNode;

import java.io.*;
import java.nio.file.Path;

public class Randomize {
    private int number;

    public static void main(String[] args) {
        Randomize rnd = new Randomize();
        rnd.random(100,"/home/almaz/Рабочий стол/test.txt");
    }

    public void random(int number, String path) {
        File file = new File(path);
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            for (int i = 0; i < number; i++) {
                int max = (int) ( 3 + (Math.random()* + 13));
                for (int j = 0; j < max; j++) {
                    outputStreamWriter.write('\n');
                    int a = (int) (Math.random() * 100);
                    int b = (int) (Math.random() * 100);
                    outputStreamWriter.write(Integer.toString(a));
                    outputStreamWriter.write(" ");
                    outputStreamWriter.write(Integer.toString(b));
                }
                outputStreamWriter.write(" ");
//                outputStreamWriter.write(Integer.toString(-1));
                outputStreamWriter.write('\n');
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
