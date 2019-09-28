package utils;

import java.util.Random;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 21:34
 * 操作数工具类
 */
public class NumberUtil {

    /**
     * 随机获取num+1个操作数的数组
     */
    static String[] getNumbers(int num, int round) {

        Random random = new Random();
        String[] numbers = new String[num];

        for (int i = 0; i < num; i++) {
            //用于判断生成整数还是分数
            int flag = (int)(Math.random()*10) % 2;

            if(flag==0){//生成整数
                 int n = random.nextInt(round);
                 numbers[i] = (n==0?1:n)+"";
            }else{//生成分数
               int numerator = (random.nextInt(round))+1;
               int denominator = (random.nextInt(round))+2;;

               while(numerator>=denominator||numerator==0||denominator==0){//判断是否为真分数，且不能生成带0的分数
                   numerator = (random.nextInt(round))+1;
                   denominator = (random.nextInt(round))+2;;
               }
                numbers[i] = numerator+"/"+denominator;
            }
        }
        return numbers;
    }

}
