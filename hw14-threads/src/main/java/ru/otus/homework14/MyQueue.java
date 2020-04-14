package ru.otus.homework14;

class MyQueue {
    private int number;
    private boolean flag = false;

    synchronized void get() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }

        System.out.println("T2:   " + number);
        flag = false;
        notify();
    }

    synchronized void put(int number) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }

        this.number = number;
        System.out.println("T1:" + number);
        flag = true;
        notify();
    }
}
