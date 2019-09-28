package utils;

import bean.Fraction;

import java.util.Stack;

/**
 * @author liuyoubin
 * @date 2019/9/28 - 15:06
 * 运算工具类
 */
public class CalculateUtil {

    /**
     * 采用调度场算法，获取指定运算式的结果值
     *
     * @param express 运算式
     * @return
     */
    public static String getExpressValue(String express){
        //运算符栈，用于存放运算符包括 +、-、*、÷、(、)
        Stack<Character>  operators = new Stack<Character>();
        //操作数栈,用于存放操作数
        Stack<Fraction> fractions = new Stack<Fraction>();
        //将表达式字符串转成字符数组
        char[] chars = express.toCharArray();
        //遍历获取处理
        for (int i=0;i<chars.length;i++) {
            //获取当前的字符
            char c = chars[i];

            if(c=='('){//如果是左括号，入栈
                operators.push(c);
            }else if(c==')'){//右括号
                //当运算符栈顶的元素不为‘(’,则继续
                while(operators.peek()!='('){
                    //拿取操作栈中的两个分数
                    Fraction fraction1 = fractions.pop();
                    Fraction  fraction2 = fractions.pop();
                    //获取计算后的值
                    int[] calculate = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    //将结果压入栈中
                    fractions.push(new Fraction(calculate[0],calculate[1]));
                }
                //将左括号出栈
                operators.pop();
            }else if(c=='+'||c=='-'||c=='*'||c=='÷'){//是运算符
                //当运算符栈不为空，且当前运算符优先级小于栈顶运算符优先级
                while(!operators.empty()&&!priority(c, operators.peek())){
                    //拿取操作栈中的两个分数
                    Fraction fraction1 = fractions.pop();
                    Fraction  fraction2 = fractions.pop();
                    //获取计算后的值
                    int[] calculate = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                            fraction2.getNumerator(), fraction2.getDenominator());
                    //将结果压入栈中
                    fractions.push(new Fraction(calculate[0],calculate[1]));
                }
                operators.push(c);
            }else{//是操作数
                if(c>='0'&&c<='9'){
                    StringBuffer buf = new StringBuffer();
                    //这一步主要是取出一个完整的数值 比如 2/5、9、9/12
                    while(i<chars.length&&(chars[i]=='/'||((chars[i]>='0')&&chars[i]<='9'))){
                        buf.append(chars[i]);
                        i++;
                    }
                    i--;
                    //到此 buf里面是一个操作数
                    String val = buf.toString();
                    //标记‘/’的位置
                    int flag = val.length();
                    for(int k=0;k<val.length();k++){
                        if(val.charAt(k)=='/'){//当获取的数值存在/则标记/的位置，便于接下来划分分子和分母生成分数对象
                            flag = k;
                        }
                    }
                    //分子
                    StringBuffer buf1 = new StringBuffer();
                    //分母
                    StringBuffer buf2 = new StringBuffer();
                    for(int j=0;j<flag;j++){
                        buf1.append(val.charAt(j));
                    }
                    //判断是否为分数
                    if(flag!=val.length()){
                        for(int q=flag+1;q<val.length();q++){
                            buf2.append(val.charAt(q));
                        }
                    }else{//如果不是分数则分母计为1
                        buf2.append('1');
                    }
                    //入栈
                    fractions.push(new Fraction(Integer.parseInt(buf1.toString()), Integer.parseInt(buf2.toString())));
                }
            }
        }

        while(!operators.empty()){
            Fraction fraction1 = fractions.pop();
            Fraction  fraction2 = fractions.pop();
            //获取计算后的值
            int[] calculate = calculate(operators.pop(), fraction1.getNumerator(), fraction1.getDenominator(),
                    fraction2.getNumerator(), fraction2.getDenominator());
            //将结果压入栈中
            fractions.push(new Fraction(calculate[0],calculate[1]));
        }

        //计算结果
        Fraction result = fractions.pop();
        //获取最终的结果(将分数进行约分)
        return getFianlResult(result);

    }

    private static String getFianlResult(Fraction result) {
        //获取最大公约数
        int gcd = gcd(result.getNumerator(),result.getDenominator());

        if(result.getDenominator()/gcd==1){//分母为1
            return String.valueOf(result.getNumerator()/gcd);
        }else{
            if(result.getNumerator()>result.getDenominator()){
                result = getRealFraction(result);
                return result.getInter()+"'"+result.getNumerator()/gcd+"/"+result.getDenominator()/gcd;
            }else{
                return result.getNumerator()/gcd+"/"+result.getDenominator()/gcd;
            }
        }
    }

    /**
     * 化成真分数
     * @param result
     * @return
     */
    private static Fraction getRealFraction(Fraction result){
        int numerator = result.getNumerator();
        int denominator = result.getDenominator();
        int newNumerator = numerator % denominator;
        int inter = numerator/denominator;

        Fraction fraction = new Fraction(newNumerator, denominator);
        fraction.setInter(inter);
        return fraction;
    }

    public static void main(String[] args) {
        Fraction realFraction = getRealFraction(new Fraction(20, 3));
        System.out.println(realFraction);
    }
    /**
     * 判断两个运算符的优先级
     * 当opt1的优先级大于opt2时返回true
     * 这是根据调度场算法给出的实现
     * @return
     */
    private static boolean priority(char opt1,char opt2){
//        if(opt2=='('||opt2==')'){
//            return false;
//        }else
        if((opt1=='+'||opt1=='-')&&(opt2=='*'||opt2=='÷')){
            return false;
        }else if((opt1=='+'||opt1=='-')&&(opt2=='+'||opt2=='-')){
            return false;
        }else if((opt1=='*'||opt1=='÷')&&(opt2=='*'||opt2=='÷')){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 对两个分数进行相应的运算，获取结果
     * @param opt 运算符
     * @param numerator1 分子1
     * @param denominator1 分母1
     * @param numerator2 分子2
     * @param denominator2 分母2
     * @return 结果
     */
    static int[] calculate(Character opt,int numerator1,int denominator1,int numerator2,int denominator2){
        //结果数组,存放结果的分子与分母
        int[] result = new int[2];
        /**
         * 这里有一个陷阱，因为用于计算的两个数是通过栈来存储，所以取出计算结果的时候两个数的顺序会颠倒
         * 比如式子 1/2*9/12 我取出来的时候其实是 9/12 和 1/2 所以调用此函数的时候是calculate('*',9,12,1,2),所以下面的实现要注意不要踩坑
         */
        switch (opt){
            case '+':
                result[0] = numerator1*denominator2 + numerator2*denominator1; result[1]= denominator1*denominator2;
                return result;
            case '-':
                result[0] = numerator2*denominator1 - numerator1*denominator2; result[1]= denominator1*denominator2;
                return result;
            case '*':
                result[0] = numerator1*numerator2; result[1] = denominator1*denominator2;
                return result;
            case '÷':
                result[0] = numerator2*denominator1; result[1] = numerator1*denominator2;
                return result;
        }
        return null;
    }

    /**
     * 获取分子分母的最大公约数
     * @param numerator 分子
     * @param denominator 分母
     * @return 最大公约数
     */
    private static int gcd(int numerator,int denominator){
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        while (denominator != 0) {
            // 求余
            int remainder = numerator % denominator;
            // 交换数，等同递归调用
            numerator = denominator;
            denominator = remainder;
        }
        return numerator;
    }
}