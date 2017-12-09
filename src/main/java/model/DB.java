package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/15.
 */
/*
*  为了简单，这里没有用数据库，题目信息临时创建，
*  保存在内存中。因为对不同层次的考生一道题目在不同试卷中的分数可能不一样，
*  因此题目分数一般是老师出卷时定的，不保存在题库中。且单选，多选，判断题每题分数应该相同，
*  填空题一般根据空数来定分数，而问答题一般根据题目难度来定的，
*  因此这里的单选、多选、判断分数相同，填空空数取1-4间的随机数，填空题分数即为空数，
*  问答题即为该题难度系数*10取整。这里各种题型均为1000题，
*  具体应用时改为数据库即可。
* */


public class DB {
    private List<Problem> problemDB;

    public DB() {

        problemDB = new ArrayList<Problem>();
        //一个题目
        Problem model;

        Random rand = new Random();
        List<Integer> Points;

        for (int i = 1; i <= 5000; i++)
        {
            model = new Problem();
            model.id = i;

            //试题难度系数取0.3到1之间的随机值
            model.difficulty = (rand.nextInt(70)+30) * 0.01;

            //单选题1分
            if (i < 1001)
            {
                model.type = 1;
                model.score = 1;
            }

            //单选题2分
            if (i > 1000 && i < 2001)
            {
                model.type = 2;
                model.score = 2;
            }

            //判断题2分
            if (i > 2000 && i < 3001)
            {
                model.type = 3;
                model.score = 2;
            }

            //填空题1—4分
            if (i > 3000 && i < 4001)
            {
                model.type = 4;
                model.score = rand.nextInt(4) + 1;
            }

            //问答题分数为难度系数*10
            if (i > 4000 && i < 5001)
            {
                model.type = 5;
                model.score = model.difficulty > 0.3 ? (int)(model.difficulty * 10) : 3;
            }

            Points = new ArrayList<Integer>();
            //每题1到4个知识点
            int count = rand.nextInt(4) + 1;
            for (int j = 0; j < count; j++)
            {
                Points.add(rand.nextInt(99) + 1);
            }
            model.Points = Points;
            problemDB.add(model);
        }

    }
}
