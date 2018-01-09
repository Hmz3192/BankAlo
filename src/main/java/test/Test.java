package test;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static int num = 10;

    public static void main(String[] args) {

        Thread a = new Thread(new MyThread(1));
        Thread b = new Thread(new MyThread(2));

        a.start();
        b.start();

        /*
         * 主线程等待子线程完成，然后再打印数值
         */
        try {
            a.join();
            b.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(num);
    }

    static class MyThread implements Runnable {
        /**
         * 计算类型，1表示减法，其他的表示加法
         */
        private int type;
        private static ReentrantLock lock = new ReentrantLock();
        public MyThread(int type) {
            this.type = type;
        }

        public void run() {
            if (type == 1){
                for (int i = 0; i < 10; i++)
                    lock.lock();
                    Test.num--;
                    lock.unlock();
            }else
                for (int i = 0; i < 10; i++)
                    lock.lock();
                    Test.num++;
                    lock.unlock();
        }
    }
}
