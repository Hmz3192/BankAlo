package model;

import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/15.
 */

/* 试卷一般包含试卷编号，试卷名称，考试时间，难度系数，知识点分布，总题数， 总分数，
各种题型所占比率等属性，这里为简单去掉了试卷名称跟考试时间。
其中的知识点分布即老师出卷时选定本试卷要考查的知识点，
这里用List<int>（知识点编号集合）表示。*/
public class Paper {
//    试卷编号
    public int ID;
//    总分数
    public int TotalScore;
//    难度系数
    public double Difficulty;
//    知识点分布
    public List<Integer> Points;

//    各种题型所占比率
    public int[] EachTypeCount;

    public Paper(int size) {
        System.out.println(size);

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }

    public double getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(double difficulty) {
        Difficulty = difficulty;
    }

    public List<Integer> getPoints() {
        return Points;
    }

    public void setPoints(List<Integer> points) {
        Points = points;
    }

    public int[] getEachTypeCount() {
        return EachTypeCount;
    }

    public void setEachTypeCount(int[] eachTypeCount) {
        EachTypeCount = eachTypeCount;
    }
}
