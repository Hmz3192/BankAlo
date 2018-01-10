package pro1;

import org.junit.Test;

public class MatrixTester {

    //初始化矩阵
    @Test
    public void test1(){
        Matrix m = new Matrix(2, 3);
        System.out.println(m.toString());
        m.setValue(1, 2, 100);
        System.out.println(m.toString());
        System.out.println("列数"+m.getNumCols() + "||行数"+m.getNumRows());
    }
    //测试读取文件方式初始化矩阵
    @Test
    public void test2(){
//        Matrix m = new Matrix(2, 3);
        Matrix m = new Matrix("E:\\WorkSpace\\IdeaWorkSpace\\BankAlo\\src\\main\\java\\pro1\\matrix.txt");
        System.out.println(m.toString());
        m.setValue(1, 2, 100);
        System.out.println(m.toString());
        System.out.println("列数"+m.getNumCols() + "||行数"+m.getNumRows());

    }
    //串行乘法
    @Test
    public void testMult() {
        Matrix m = new Matrix(2, 2);
        Matrix n = new Matrix(2, 2);
        System.out.println("矩阵1：");
        System.out.println(m.toString());
        System.out.println("矩阵2：");
        System.out.println(n.toString());
        Matrix matrix = m.normalMultiply(n);
        System.out.println("相乘之后：");
        System.out.println(matrix.toString());
    }
    //并行乘法，并于串行乘法比较时间
    @Test
    public void testMultThread() {
        long startTime;
        long endTime;
         /*-------------------------------------并行计算*/
        Matrix m1 = new Matrix(1000, 1000);
        Matrix n1 = new Matrix(1000, 1000);
        Dotproductthread thread1 = new Dotproductthread(m1, n1, 0,1000,1000);
        Dotproductthread thread2 = new Dotproductthread(m1, n1, 1,1000,1000);
        //-------------------并行计算--------------------
        startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("花 " + (endTime - startTime) + "时间并行计算");
        System.out.println("结果是" + (thread1.getMatrixSum() + thread2.getMatrixSum()));

        /*-------------------------------------串行计算*/

        startTime = System.currentTimeMillis();
        m1.normalMultiply(n1);
        endTime = System.currentTimeMillis();
        System.out.println("花 " + (endTime - startTime) + "时间串行计算");
        System.out.println("结果是"+m1.getMatrixSum());
    }

}
