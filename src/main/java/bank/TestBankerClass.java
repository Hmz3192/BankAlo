package bank;

import java.util.Scanner;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/12/2.
 */
public class TestBankerClass {

    public static void main(String[] args) {
        // TODO code application logic here
        boolean Choose = true;
        String C;
        Scanner in = new Scanner(System.in);
        System.out.println("这是一个多进程，初始系统可用三类资源为{10,5,7}的银行家算法：");
        System.out.println("请输入进程数：");
        int num = in.nextInt();
        BankerClass T = new BankerClass(num);
        T.setSystemVariable();
        while (Choose == true) {
            T.setRequest();
            System.out.println("您是否还要进行请求：y/n?");
            C = in.nextLine();
            if (C.endsWith("n")) {
                Choose = false;
            }
        }
    }
}
