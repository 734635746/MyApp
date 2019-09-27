package utils;

import bean.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 22:09
 */
public class ExpressionUtil {
    public static List<Expression> generate(int n){
        List<Expression> expressions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //随机获取运算符的个数(1~3个)
            int num = (int)(Math.random()*3)+1;
            //随机获取num个运算符
            String[] curOperators = OperatorUtil.getOperators(num);
            //随机获取num+1个操作数
            Number[] curNumbers = NumberUtil.getNumbers(num);
            Expression expression = new Expression(curOperators,curNumbers);
            expressions.add(expression);
        }
        return expressions;
    }
}
