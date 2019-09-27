package utils;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 21:34
 * 操作数工具类
 */
public class NumberUtil {

    private static final int round = 3;
    /**
     * 随机获取num+1个操作数的数组
     */
    public static Number[] getNumbers(int num) {

        Number[] numbers = new Number[num+1];

        for (int i = 0; i <= num; i++) {
            int m = (int)(Math.random()*round)+1;
            numbers[i] = m;
        }

        return numbers;
    }
}
