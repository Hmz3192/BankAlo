package p2;


public class Test {
    public static void main(String[] args) {
        // new 出一个新的对象 t
        ThreadResource t = new ThreadResource();
        /**
         * 多个线程是在对同一个对象进行操作
         */
        for(int i = 0;i<12;i++) {
                Thread thread = new Thread(t, "Thread-" + i);
                thread.start();
        }
    }
}
