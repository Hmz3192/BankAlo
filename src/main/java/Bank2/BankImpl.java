package Bank2;

public class BankImpl implements Bank {
    //客户数，线程数
    private int numOfCustomers = Customer.COUNT;
    //资源数
    private int numOfResources = 3;
    //可用资源
    private int[] available;
    //最大需求
    private int[][] maximum;
    //已有资源
    private int[][] allocation;
    //仍需资源
    private int[][] need;
    //客户数组
    private Customer[] customers = new Customer[5];
    //最开始资源
    int[] initResources;


    public BankImpl(int[] initResourcesAvailable) {
        initResources = initResourcesAvailable;
        //最大需求
        maximum = new int[Customer.COUNT][initResources.length];
        //已分配
        allocation = new int[Customer.COUNT][initResources.length];
        //需要的数组
        need = new int[Customer.COUNT][initResources.length];

        available = initResourcesAvailable;

    }

    @Override
    public void addCustomer(int customerNum, int[] maxDemand) {
        int i;
        this.customers[customerNum] = new Customer(customerNum, maxDemand, this);
        for (i = 0; i < maxDemand.length; i++) {
            this.maximum[customerNum][i] = maxDemand[i];
        }
    }

    //打印出available，alloction，max，
    @Override
    public void getState() {
        System.out.println("Available=Available-Alloction.");
        System.out.println("Need=Max-Alloction.");
        for (int i = 0; i < numOfCustomers; i++) {//设置Need矩阵
            for (int j = 0; j < numOfResources; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }

        System.out.println("此时资源分配量如下：");
        System.out.println("客户  " + "   Max   " + "   Alloction " + "    Need  " + "     Available ");
        for (int i = 0; i < numOfCustomers; i++) {
            System.out.print("P" + i + "  ");
            for (int j = 0; j < numOfResources; j++) {
                System.out.print(this.maximum[i][j] + "  ");
            }
            System.out.print("|  ");
            for (int j = 0; j < numOfResources; j++) {
                System.out.print(allocation[i][j] + "  ");
            }
            System.out.print("|  ");
            for (int j = 0; j < numOfResources; j++) {
                System.out.print(need[i][j] + "  ");
            }
            System.out.print("|  ");
            if (i == 0) {
                for (int j = 0; j < numOfResources; j++) {
                    System.out.print(available[j] + "  ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public boolean requestResources(int customerNum, int[] request) {
        boolean T = true;
        //这里定义的是用来方式分配不够情况就能够初始化
        int[] AvailableOrign = available; //存储现有资源的数组，这个用来恢复原来状态
        int[][] AlloctionOrign = allocation; //存储所分配的资源的数组，这个也是用来恢复原来状态
        int[][] NeedOrign = need; //存储剩余的资源，这个用来恢复原来状态

        //判断Request是否小于Need
        if (request[0] <= need[customerNum][0] && request[1] <= need[customerNum][1] && request[2] <= need[customerNum][2]) {
            //判断Request是否小于Alloction
            if (request[0] <= available[0] && request[1] <= available[1] && request[2] <= available[2]) {
                for (int i = 0; i < numOfResources; i++) {
                    available[i] -= request[i];
                    allocation[customerNum][i] += request[i];
                    need[customerNum][i] -= request[i];
                }
            } else {
                System.out.println("当前没有足够的资源可分配，客户 P" + customerNum + "需等待。");
                //没有足够资源分配就初始化
                need = NeedOrign;
                allocation = AlloctionOrign;
                available = AvailableOrign;
                System.out.println("以恢复原来状态：");
                T = false;
            }
        } else {
            System.out.println("客户 P" + customerNum + "请求已经超出最大需求量Need.");
            //没有足够资源分配就初始化
            need = NeedOrign;
            allocation = AlloctionOrign;
            available = AvailableOrign;
            System.out.println("以恢复原来状态：");
            T = false;
        }
        if (T == true) {
            System.out.println("进入安全算法进行验证，如验证成功，则分配成功");
            SecurityAlgorithm(AvailableOrign, AlloctionOrign, NeedOrign);
            return true;
        }
        return false;

    }

    //释放资源
    //减少alloction，增加need，增加available
    @Override
    public void releaseResources(int customerNum, int[] release) {
        //判断已有的是不是>=需要释放的数量
        if (release[0] <= allocation[customerNum][0] && release[1] <= allocation[customerNum][1] && release[2] <= allocation[customerNum][2]) {
            for (int i = 0; i < numOfResources; i++) {
                available[i] += release[i];
                allocation[customerNum][i] -= release[i];
                need[customerNum][i] += release[i];
            }
            System.out.println("释放成功");
        } else {
            System.out.println("客户 P" + customerNum + "释放量已经超出最大已分配量alloction.");
            System.out.println("释放失败");
        }
    }

    //安全算法
    public void SecurityAlgorithm(int[] availableOrign, int[][] alloctionOrign, int[][] needOrign) {
        int[] Work = new int[numOfResources]; //存储正在处理的资源数组
        int[] S = new int[numOfCustomers];//安全序列
        boolean[] Finish = new boolean[numOfCustomers];
        for (int i = 0; i < numOfCustomers; i++) {
            Finish[i] = false;//初始化Finish
        }
        int count = 0;//完成进程数
        int circle = 0;//循环圈数
        for (int i = 0; i < 3; i++) {//设置工作向量
            Work[i] = available[i];
        }
        boolean flag = true;
        while (count < numOfCustomers) {
            if (flag) {
                System.out.println("客户  " + "   Work  " + "   Alloction " + "    Need  " + "     Work+Alloction ");
                flag = false;
            }
            for (int i = 0; i < numOfCustomers; i++) {

                if (Finish[i] == false && need[i][0] <= Work[0] && need[i][1] <= Work[1] && need[i][2] <= Work[2]) {//判断条件
                    System.out.print("P" + i + "  ");
                    for (int k = 0; k < numOfResources; k++) {
                        System.out.print(Work[k] + "  ");
                    }
                    System.out.print("|  ");
                    for (int j = 0; j < numOfResources; j++) {
                        Work[j] += this.allocation[i][j];
                    }
                    Finish[i] = true;//当当前进程能满足时
                    S[count] = i;//设置当前序列排号
                    count++;//满足进程数加1
                    for (int j = 0; j < numOfResources; j++) {
                        System.out.print(allocation[i][j] + "  ");
                    }
                    System.out.print("|  ");
                    for (int j = 0; j < numOfResources; j++) {
                        System.out.print(need[i][j] + "  ");
                    }
                    System.out.print("|  ");
                    for (int j = 0; j < numOfResources; j++) {
                        System.out.print(Work[j] + "  ");
                    }
                    System.out.println();
                }
            }
            circle++;//循环圈数加1
            if (count == numOfCustomers) {//判断是否满足所有进程需要
                System.out.print("此时存在一个安全序列：");
                for (int i = 0; i < this.numOfCustomers; i++) {//输出安全序列
                    System.out.print("P" + S[i] + " ");
                }
                System.out.println("故当前可分配！");
                break;//跳出循环
            }
            if (count < circle) {//判断完成进程数是否小于循环圈数
                count = 5;
                System.out.println("当前系统处于不安全状态，故不存在安全序列。");
                need = needOrign;
                allocation = alloctionOrign;
                available = availableOrign;
                System.out.println("以恢复原来状态：");
            }
        }
    }

}
