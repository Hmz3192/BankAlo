package pro1;

public class Dotproductthread extends Thread{

    private Matrix matrixA = new Matrix();
    private Matrix matrixB = new Matrix();
    private Matrix matrixC = new Matrix();
    private int start ;
    private int end ;
    private long sum = 0 ;
    //初始化，共并行乘法使用
    public Dotproductthread(Matrix matrixA, Matrix matrixB, int st, int en,int size){
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.start = st;
        this.end = en;
        //按照a，b的大小
        int[][] matrix = new int[size][size];
        this.matrixC.setMatrix(matrix);
    }
    //并行乘法
    public Matrix mutithreadedMultiply(Matrix input1,Matrix input2){
        int i,j,k;
        for( i=start; i< end; i += 2)
        {
            for( j=0;j<end;j++)
            {
                matrixC.matrix[i][j] = 0;
                for( k=0; k< end;k++)
                {
                    matrixC.matrix[i][j]+=  input1.matrix[i][k]*  input2.matrix[k][j];
                }
            }
        }
        for( i=start; i<end; i+=2)
            for( j=0; j<end; j++)
                sum += matrixC.matrix[i][j];
        return matrixC;
    }
    public void run(){
        matrixC = mutithreadedMultiply(matrixA,matrixB);

    }

    public long getMatrixSum() {
        return this.sum;
    }

    public Matrix getMatrix() {
        return this.matrixC;
    }
}
