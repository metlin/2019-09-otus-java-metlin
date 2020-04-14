package ru.otus.homework14;

public class Main {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        new Thread1(myQueue);
        new Thread2(myQueue);
    }
}
