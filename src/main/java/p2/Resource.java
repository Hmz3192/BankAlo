package p2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {

    private int available_resources;
    private Lock lock = new ReentrantLock();


    public Resource(int available_resources) {
        this.available_resources = available_resources;
    }

    public  int decrease_count(int count) {
//        do {
//            lock.lock();
//            System.out.println("进程阻塞");
//        } while (count > available_resources);
//        System.out.println("解锁");
        if (count < available_resources) {
            available_resources -= count;
        }
        lock.unlock();
        return 0;
    }

    public  int increase_count(int count) {
        available_resources += count;
        return 0;
    }

    public int getAvailable_resources() {
        return this.available_resources;
    }
}
