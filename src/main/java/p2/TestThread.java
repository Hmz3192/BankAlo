package p2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
    public static void main(String[] args) {
        // new 出一个新的对象 t
        MyThread t = new MyThread();
        /**
         * 两个线程是在对同一个对象进行操作
         */
        Thread ta = new Thread(t, "Thread-A");
        Thread tb = new Thread(t, "Thread-B");
        ta.start();
        tb.start();
    }
}

class MyThread implements Runnable {
    // 同时被两个线程共同操作，可能会造成线程竞争
    private Resource resource = new Resource(10);
    // 声明锁
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // 加锁
        lock.lock();
        for (int i = 0; i < 5; i++) {
            resource.decrease_count(1);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " → 当前剩余资源为： " + resource.getAvailable_resources());
        }
        // 解锁
        lock.unlock();
    }
}
