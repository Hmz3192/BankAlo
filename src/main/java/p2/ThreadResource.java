package p2;

import java.util.Random;

public class ThreadResource implements Runnable{

    private Resource resource = new Resource(10);


    @Override
    public synchronized void run() {
        for(int i=0;i<5;i++) {
            resource.decrease_count(10);
            System.out.println("线程名" + Thread.currentThread().getName() + "请求资源 || 剩余资源量" + resource.getAvailable_resources());
            try {
                Thread.currentThread().sleep(1000);
                resource.increase_count(10);
                System.out.println("在延迟1s之后，线程名" + Thread.currentThread().getName() + "返回资源 || 剩余资源量" + resource.getAvailable_resources());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
