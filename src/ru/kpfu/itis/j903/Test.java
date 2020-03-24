package ru.kpfu.itis.j903;

public class Test {

    public static void main(String[] args) {
        Polinom p  = new Polinom("/home/almaz/Загрузки/test.txt");
        System.out.println(p);
//        p.derivate();
//        p.deleteOdd();
        System.out.println(p.value(2));
    }

}
