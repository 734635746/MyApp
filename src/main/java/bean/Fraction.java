package bean;

/**
 * @author liuyoubin
 * @date 2019/9/28 - 11:53
 * 分数类
 */
public class Fraction {
    private int inter;//整数部分
    private int numerator;//分子
    private int denominator;//分母

    @Override
    public String toString() {
        return "Fraction{" +
                "inter=" + inter +
                ", numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getInter() {
        return inter;
    }

    public void setInter(int inter) {
        this.inter = inter;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
}
