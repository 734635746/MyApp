package arithmetic;

import arithmetic.utils.ExpressionUtil;
import arithmetic.utils.PrintFileUtil;
import arithmetic.utils.ValidateUtil;

import java.util.Scanner;

/**
 * @author liuyoubin
 * @date 2019/9/27 - 21:08
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("***************欢迎使用四则运算题目生成程序****************");
        System.out.println("***************使用-n 参数控制题目生成数目 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围****************");
        System.out.println("***************使用 -e <exercisefile>.txt -a <answerfile>.txt 检查答案的正确率***************");

        while(true){

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(command.equals("bye")){
                break;
            }
            String[] split = ValidateUtil.checkParams(command);//command.split(" ");先校验输入避免出现不可靠输入导致程序退出
            if (split!=null){
                if (split[0].equals("-n")) {//-n i -r j
                    int num = Integer.valueOf(split[1]);
                    if (num <= 0) {
                        System.out.println("-n参数输入错误，请重新输入");
                        //break;//break->程序退出
                    }else {
                        int round = Integer.valueOf(split[3]);
                        if (round <= 0) {
                            System.out.println("-r参数输入错误，请重新输入");
                        } else {
                            //获取运算式数组
                            String[] express = ExpressionUtil.generate(num, round);
                            PrintFileUtil.printExerciseFileAndAnswerFile(express);
                            //System.out.println("操作成功！！！");//解决文件操作失败但提示操作成功
                        }
                    }
                }else {// -e x -a y
                    String exerciseFileUrl = split[1];
                    String answerFileUrl = split[3];
                    //验证答案
                    PrintFileUtil.validateAnswerFile(exerciseFileUrl, answerFileUrl);
                }
            }else {
                System.out.println("参数输入有误，请重新输入");
            }


            //if(split[0].equals("-n")){
            //    int num = Integer.valueOf(split[1]);
            //    if(num<=0){
            //        System.out.println("-n参数输入错误，请重新输入");
            //        //break;//break->程序退出
            //    }
            //    if(split.length<3 || !split[2].equals("-r")){ //长度判断(NullPointer)
            //        System.out.println("请使用-r 指定数值范围");
            //    }else{
            //        int round = 0;
            //        if (split.length>3){
            //            round = Integer.valueOf(split[3]);
            //        }
            //        if(round<=0){
            //            System.out.println("-r参数输入错误，请重新输入");
            //        }else {
            //            //获取运算式数组
            //            String[] express = ExpressionUtil.generate(num, round);
            //            PrintFileUtil.printExerciseFileAndAnswerFile(express);
            //            //System.out.println("操作成功！！！");//解决文件操作失败但提示操作成功
            //        }
            //    }
            //
            //}else if (split.length>3){
            //    if(split[0].equals("-e")&&split[2].equals("-a")) {
            //        String exerciseFileUrl = split[1];
            //        String answerFileUrl = split[3];
            //        //验证答案
            //        PrintFileUtil.validateAnswerFile(exerciseFileUrl, answerFileUrl);
            //    }
            //}else{
            //
            //}
        }
    }

}
