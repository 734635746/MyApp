package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 22:09
 */
public class ExpressionUtil {

    /**
     * 获取指定个数的运算式字符串
     */
    public static String[] generate(int n,int round){
        String[] expressStr = new String[n];

        for (int i = 0; i < n; i++) {
            //随机获取运算符的个数(1~3个)
            int num = (int)(Math.random()*3)+1;
            //随机获取num个运算符
            Character[] curOperators = OperatorUtil.getOperators(num);
            //随机获取num+1个操作数
            String[] curNumbers = NumberUtil.getNumbers(num+1,round);
            //获取运算式表达式
            String express = getExpressStr(curOperators, curNumbers);

            expressStr[i] = express;
        }
        return expressStr;
    }

    /**
     * 根据运算符数组和操作数数组生成运算式表达式
     * @param curOperators 运算符数组
     * @param curNumbers 操作数数组
     * @return 运算式字符串
     */
    private static String getExpressStr(Character[] curOperators, String[] curNumbers){
        //操作数的数量
        int number = curNumbers.length;
        //随机判断是否生成带括号的运算式
        int isAddBracket = (int)(Math.random()*10) % 2;
        //随机生成器
        Random random = new Random();

        if(isAddBracket==1){//生成带括号的表答式
            //当标记为1时代表该操作数已经添加了左括号
            int[] lStamp = new int[number];
            //当标记为1时代表该操作数已经添加了右括号
            int[] rStamp = new int[number];
            //遍历操作数数组，随机添加括号
            for (int index=0;index<number-1;index++) {
                int n = (int)(Math.random()*10) % 2;
                if(n == 0 && rStamp[index] != 1) {
                    lStamp[index] = 1;
                    curNumbers[index] = "(" + curNumbers[index];  //操作数之前加上左括号
                    int k = number - 1;
                    //生成右括号的位置
                    int q = random.nextInt(k)%(k-index) + (index+1);
                    //如果当前操作数有左括号，则重新生成优括号位置
                    while (lStamp[q] == 1){
                        q = random.nextInt(k)%(k-index) + (index+1);
                    }
                    curNumbers[q] = curNumbers[q] +")";
                    rStamp[q] = 1;
                }
            }
        }

        //将运算符数组和操作数数组拼成一个运算式字符串
        StringBuilder str = new StringBuilder(curNumbers[0]);
        for (int k = 0; k < curOperators.length; k++) {
            str.append(curOperators[k]).append(curNumbers[k + 1]);
        }
        return str.toString();
    }
}
