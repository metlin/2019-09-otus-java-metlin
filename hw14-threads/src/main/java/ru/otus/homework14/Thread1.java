package ru.otus.homework14;

public class Thread1 implements Runnable {

    private final MyQueue queue;

    Thread1(MyQueue queue) {
        this.queue = queue;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            for(int i = 0; i <= 10; i++) {
                queue.put(i);
            }

            for(int i = 9; i >= 1; i--) {
                queue.put(i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
