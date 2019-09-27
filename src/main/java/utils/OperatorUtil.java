package utils;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 21:34
 * 运算符操作工具类
 */
public class OperatorUtil {

    private final static String[] operatorTypes = new String[]{"*", "+", "-", "/"};

    /**
     * 随机获取num个运算符的数组
     */
    public static String[] getOperators(int num) {

        String[] operators = new String[num];

        for (int i = 0; i < num; i++) {
            //随机获取运算符的类型(0~3 代表4个运算符的类型)
            int operatorTypeIndex = (int)(Math.random()*4);
            String operatorType = operatorTypes[operatorTypeIndex];
            operators[i] = operatorType;
        }

        return operators;
    }
}
