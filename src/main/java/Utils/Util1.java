package Utils;

import java.util.*;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/11/15.
 */
public class Util1 {

    //求两个数组的交集
    public static List<Integer> intersect(List<Integer> arr1, List<Integer>  arr2) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int str : arr1) {
            if (!map.containsKey(str)) {
                map.put(str, Boolean.FALSE);
            }
        }
        for (int str : arr2) {
            if (map.containsKey(str)) {
                map.put(str, Boolean.TRUE);
            }
        }

        for (Map.Entry<Integer, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }

        return list;
    }


    public   static   List  removeDuplicateWithOrder(List list)   {
        Set set  =   new HashSet();
        List newList  =   new  ArrayList();
        for  (Iterator iter  =  list.iterator(); iter.hasNext();)   {
            Object element  =  iter.next();
            if  (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println( " remove duplicate "   +  list);

        return list;
    }
}
