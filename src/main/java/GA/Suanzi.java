package GA;

import Utils.Util1;
import model.Paper;
import model.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/16.
 */
public class Suanzi {

    //  选择算子
    public List<Unit> Select(List<Unit> unitList, int count) {
        List<Unit> selectedUnitList = new ArrayList<Unit>();

        //种群个体适应度和
        double AllAdaptationDegree = 0;
        for (Unit u : unitList) {
            AllAdaptationDegree += u.AdaptationDegree;
        }

        Random rand = new Random();
        while (selectedUnitList.size() != count) {
            //选择一个0—1的随机数字
            double degree = 0.00;
            double randDegree = (rand.nextInt(99) + 1) * 0.01 * AllAdaptationDegree;

            //选择符合要求的个体
            for (int j = 0; j < unitList.size(); j++) {
                degree += unitList.get(j).AdaptationDegree;
                if (degree >= randDegree) {
                    //不重复选择
                    for (Unit unit : selectedUnitList) {
                        if (unit.equals(unitList.get(j))) {
                            selectedUnitList.add(unitList.get(j));
                        }
                    }
                    break;
                }
            }
        }
        return selectedUnitList;
    }


    //  交叉算子
    public List<Unit> Cross(List<Unit> unitList, int count, Paper paper) {
        List<Unit> crossedUnitList = new ArrayList<Unit>();
        Random rand = new Random();
        while (crossedUnitList.size() != count) {
            //随机选择两个个体
            int indexOne = rand.nextInt(unitList.size());
            int indexTwo = rand.nextInt(unitList.size());
            Unit unitOne;
            Unit unitTwo;
            if (indexOne != indexTwo) {
                unitOne = unitList.get(indexOne);
                unitTwo = unitList.get(indexTwo);

                //随机选择一个交叉位置
                int crossPosition = rand.nextInt(unitOne.ProblemCount - 2);

                //保证交叉的题目分数合相同
                double scoreOne = unitOne.ProblemList.get(crossPosition).score + unitOne.ProblemList.get(crossPosition + 1).score;
                double scoreTwo = unitTwo.ProblemList.get(crossPosition).score + unitTwo.ProblemList.get(crossPosition + 1).score;
                if (scoreOne == scoreTwo) {
                    //两个新个体
                    Unit unitNewOne = new Unit();

                    unitNewOne.ProblemList = (unitOne.ProblemList);
                    Unit unitNewTwo = new Unit();
                    unitNewTwo.ProblemList = (unitTwo.ProblemList);

                    //交换交叉位置后面两道题
                    for (int i = crossPosition; i < crossPosition + 2; i++) {
                        unitNewOne.ProblemList.set(i, new Problem(unitTwo.ProblemList.get(i)));
                        unitNewTwo.ProblemList.set(i, new Problem(unitOne.ProblemList.get(i)));
                    }

                    //添加到新种群集合中
                    unitNewOne.ID = crossedUnitList.size();
                    unitNewTwo.ID = unitNewOne.ID + 1;
                    if (crossedUnitList.size() < count) {
                        crossedUnitList.add(unitNewOne);
                    }
                    if (crossedUnitList.size() < count) {
                        crossedUnitList.add(unitNewTwo);
                    }

                }
            }

            //过滤重复个体
            crossedUnitList = Util1.removeDuplicateWithOrder(crossedUnitList);

        }

        //计算知识点覆盖率及适应度
        crossedUnitList = InitialPopulation.GetKPCoverage(crossedUnitList, paper);
//        crossedUnitList = InitialPopulation.GetAdaptationDegree(crossedUnitList, paper, crossedUnitList.get(i), difficulty);

        return crossedUnitList;
    }
}
