package Utils;

import model.Paper;

import java.io.*;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/16.
 */
public class test {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\ThinKPad\\Desktop\\test\\src.txt");
        //创建输入流
        FileInputStream fis = null;
        //创建输出流
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        PrintWriter pw = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream("C:\\Users\\ThinKPad\\Desktop\\test\\target.txt");
            String s = null;
            int length = 0;
            byte[] buf = new byte[1024];
            while ((length = fis.read(buf)) != -1) {
                //将小数从文件a.txt 读出来 并放到数组中
                s = new String(buf, 0, length, "UTF-8");
                String[] str = s.split(",");


                double count = 0;
                for (int i = 0; i < str.length; i++) {
                    //求小数和
                    count += Double.parseDouble(str[i]);
                }
                //求平均值
                double avg = count / str.length;
                //使文件 c.txt写入数据
                osw = new OutputStreamWriter(fos);
                pw = new PrintWriter(osw);
                //输入平均值
                pw.println(avg);
                System.out.println("成功输入");


            }
//关闭流
            osw.close();
            pw.close();
            fis.close();
            fos.close();
        } catch (IOException e) {
// TODO: handle exception
            e.printStackTrace();
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    fis = null;
                }
                if (null != fos) {


                    try {
                        fos.close();
                    } catch (IOException e) {
                        fos = null;
                    }


                }
                if (null != osw) {
                    try {
                        osw.close();
                    } catch (IOException e) {
                        osw = null;
                    }
                }
                if (null != pw) {
                    try {
                        pw.close();
                    } catch (Exception e) {
                        pw = null;
                    }
                }
            }
        }
    }
}
