package GA;

import Utils.Util1;
import model.Paper;
import model.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/15.
 */

/*
* 下面即来产生初始种群，按
* 个体数量，
* 期望试卷知识点分布，
* 各类型题目数等
* 限制产生初始种群：
* */
public class InitialPopulation {

    public List<Unit> CSZQ(int count, Paper paper,/*题库*/ List<Problem> problemList) {

        List<Unit> unitList = new ArrayList<Unit>();

        int[] eachTypeCount = paper.EachTypeCount;

        Unit unit;
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            unit = new Unit();
            unit.ID = i + 1;
            unit.AdaptationDegree = 0.00;

            //总分限制
            while (paper.TotalScore != unit.getSumScore()) {
                unit.ProblemList.clear();

                //各题型题目数量限制
                for (int j = 0; j < eachTypeCount.length; j++) {
//                    new 一种题型中的所有符合问题，选择出题类型相同，之前不包含的题目
                    List<Problem> allfitProblems = new ArrayList<Problem>();
                    for (Problem problem : problemList) {

                        if (problem.type == (j + 1)) {
//                            判断试卷之前没有添加这个题目
                            for (Problem problem1 : allfitProblems) {
                                if (problem1.id != problem.id) {
                                    allfitProblems.add(problem);
                                }
                            }
                        }
                    }
                    List<Problem> oneTypeProblem = allfitProblems;
                    Problem temp = new Problem();
//                    随机抽到的题目在添加到队列之后，和最后k+1位调换位子，这样就下次抽就不会重复
                    for (int k = 0; k < eachTypeCount[j]; k++) {
                        //选择不重复的题目
                        int index = rand.nextInt(oneTypeProblem.size() - k);
                        unit.ProblemList.add(oneTypeProblem.get(index));
                        temp = oneTypeProblem.get(oneTypeProblem.size() - 1 - k);
                        oneTypeProblem.set(oneTypeProblem.size() - 1 - k, oneTypeProblem.get(index));
                        oneTypeProblem.set(index, temp);
                    }
                }
            }
            unitList.add(unit);


            //计算知识点覆盖率及适应度
            unitList = GetKPCoverage(unitList, paper);
            unitList = GetAdaptationDegree(unitList, paper, unitList.get(i).getKPCoverage(), unitList.get(i).getDifficulty());

            return unitList;
        }
        return unitList;
    }

    public static List<Unit> GetKPCoverage(List<Unit> unitList, Paper paper) {
        List<Integer> kp;
        for (int i = 0; i < unitList.size(); i++) {
            kp = new ArrayList<Integer>();
            for (Problem problem : unitList.get(i).getProblemList()) {
                kp = problem.getPoints();

            }
            //个体所有题目知识点并集跟期望试卷知识点交集
            List<Integer> intersect = Util1.intersect(kp, paper.Points);
            unitList.get(i).KPCoverage = intersect.size() * 1.00 / paper.Points.size();

        }
        return unitList;
    }

    public static List<Unit> GetAdaptationDegree(List<Unit> unitList, Paper paper, double KPCoverage, double Difficulty) {
        unitList = GetKPCoverage(unitList, paper);
        for (int i = 0; i < unitList.size(); i++) {
            unitList.get(i).AdaptationDegree = 1 - (1 - unitList.get(i).KPCoverage) * KPCoverage - Math.abs(unitList.get(i).Difficulty - paper.Difficulty) * Difficulty;
        }

        return unitList;
    }

}
