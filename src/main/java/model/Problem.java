package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/15.
 */

/*
* 一道题至少有一个知识点，为简单易懂，知识点用List<int> 表示（知识点编号集合）。
* */
public class Problem {
    // 编号
    public int id;
    // 题型（1、2、3、4、5对应单选，多选，判断，填空，问答）
    public int type;
    // 分数
    public int score;
    // 难度系数
    public double difficulty;
    // 知识点
    List<Integer> Points;

    public Problem(Problem p) {

        this.id = p.id;
        this.type = p.type;
        this.score = p.score;
        this.difficulty = p.difficulty;
        this.Points = p.Points;
    }

    public Problem() {
        id = 0;
        type = 0;
        score = 0;
        difficulty = 0.00;
        Points = new ArrayList<Integer>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public List<Integer> getPoints() {
        return Points;
    }

    public void setPoints(List<Integer> points) {
        Points = points;
    }
}
