package pro1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Matrix {

    int row;//矩阵行数
    int col;//矩阵列数
    int[][] matrix;
    int num;


    private long sum = 0;

    public Matrix() {
    }


    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    //create mxn Matrix with random entries
    public Matrix(int row,int col) {
        this.col = col; // lie
        this.row = row; //hang
        matrix = new int[row][col];
        for (int i = 0; i < row; i ++) {
            for(int j = 0;j<col;j++) {
                num = new Random().nextInt(10);
                matrix[i][j] = num;
            }
        }
    }
    //create a matrix from a file
    public Matrix(String url) {
        File file = new File(url);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String temp = null;
            int fileRow = 0;
            List<String> number = new ArrayList<String>();
            while((temp = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] split = temp.split(" ");
                for(int j=0;j<split.length;j++) {
                    number.add(split[j]);
                }
                col = split.length;
                fileRow++;
//                result.append(System.lineSeparator()+st);
            }

            matrix = new int[fileRow][col];
            int count = 0;
            for (int i = 0; i < fileRow; i ++) {
                for(int j = 0;j<col;j++) {
                    matrix[i][j] = Integer.parseInt(number.get(count));
                    count++;
                }
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
//        return result.toString();
    }
    //create mxn Matrix with val in every cell
    public Matrix(int row,int col, int val) {
        this.col = col; // lie
        this.row = row; //hang
        matrix = new int[row][col];
        for (int i = 0; i < row; i ++) {
            for(int j = 0;j<col;j++) {
                matrix[i][j] = val;
            }
        }
    }


    public int getNumRows() {
        return this.matrix.length;
    }

    public int getNumCols() {
        return this.matrix[0].length;

    }

    public void setValue(int row,int col,int val){
        matrix[row][col] = val;
    }

    public String toString() {
        if(matrix==null){
            return "矩阵为空，请输入一个矩阵";
        }
        String s ="";
        row = matrix.length;
        col = matrix[row-1].length;

        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                s += matrix[i][j]+" ";
            }
            s += "\r\n";
        }

        return s;
    }

    //矩阵乘法
    public Matrix normalMultiply(Matrix input){
        if(this.matrix[0].length != input.matrix[0].length){
            System.out.println("两个矩阵不能相乘");
            return null;
        }
        int[][] result = new int[this.matrix.length][input.matrix[0].length];
        for(int row = 0;row < this.matrix.length;row++){
            for(int col = 0;col < this.matrix[0].length;col++){
                int num = 0;
                for(int i = 0;i <this.matrix.length;i++){
                    num+=this.matrix[row][i]*input.matrix[i][col];
                }

                result[row][col]=num;
            }
        }

        Matrix matrixResult = new Matrix();
        matrixResult.setMatrix(result);
        for(int i=0; i<matrixResult.matrix.length; i++)
            for(int j=0; j<matrixResult.matrix[0].length; j++)
                this.sum += matrixResult.matrix[i][j];
        return matrixResult;
    }


    public long getMatrixSum() {

        return this.sum;
    }


}
