package GA;

import model.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/15.
 */


/*
* 这里保证题数跟总分达到出卷要求即可，但为操作方便，
* 这里再定义一个种群个体实体类Unit，
* 包含编号、适应度、题数、总分、难度系数、知识点分布、包含的题目等信息
* */
public class Unit {


    public List<Problem> ProblemList;
    //    知识点分布
    public double KPCoverage;
    //    适应度
    public double AdaptationDegree;
    // 编号
    public int ID;

    /// 题目数量
    public int ProblemCount;

    /// 难度系数（本试卷所有题目分数*难度系数/总分）
    public double Difficulty;

    /// 总分
    public int SumScore;



    public Unit() {
        ID = 0;
        AdaptationDegree = 0.00;
        KPCoverage = 0.00;
        ProblemList = new ArrayList<Problem>();
    }

    public int getSumScore() {

        int sum = 0;

        for (Problem problem : ProblemList) {
            sum += problem.score;
        }
        return sum;
    }

    public int getProblemCount() {
        return ProblemList.size();
    }

    public double getDifficulty() {
        double diff = 0.00;
        for (Problem problem : ProblemList) {
            diff += problem.difficulty * problem.score;
        }


        return diff / SumScore;
    }

    public List<Problem> getProblemList() {
        return ProblemList;
    }

    public void setProblemList(List<Problem> problemList) {
        ProblemList = problemList;
    }

    public double getKPCoverage() {
        return KPCoverage;
    }

    public void setKPCoverage(double KPCoverage) {
        this.KPCoverage = KPCoverage;
    }

    public double getAdaptationDegree() {
        return AdaptationDegree;
    }

    public void setAdaptationDegree(double adaptationDegree) {
        AdaptationDegree = adaptationDegree;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }




}
