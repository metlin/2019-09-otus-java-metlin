package ru.otus.homework14;

public class Thread2 implements Runnable {

    private final MyQueue queue;

    Thread2(MyQueue queue) {
        this.queue = queue;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            queue.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
