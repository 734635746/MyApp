package utils;

import constant.SymbolConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/28 - 19:05
 * 生成文件的工具类
 */
public class PrintFileUtil {

    /**
     * 根据运算式子生成练习文件和答案文件
     * @param express
     */
    public static void printExerciseFileAndAnswerFile(String[] express) {
        File exerciseFile = new File(SymbolConstant.PRINT_FILE_UEL, "四则运算题目1.txt");
        File answerFile = new File(SymbolConstant.PRINT_FILE_UEL, "答案1.txt");
        try {

            OutputStream exerciseFileOutputStream = new FileOutputStream(exerciseFile);
            OutputStream answerFileOutputStream = new FileOutputStream(answerFile);
            StringBuffer exerciseBuffer = new StringBuffer();
            StringBuffer answerFileBuffer = new StringBuffer();
            for (String exp : express) {
                exerciseBuffer.append(exp+"\r\n");
                answerFileBuffer.append(CalculateUtil.getExpressValue(exp)+"\r\n");
            }
            exerciseFileOutputStream.write(exerciseBuffer.toString().getBytes());
            answerFileOutputStream.write(answerFileBuffer.toString().getBytes());
            exerciseFileOutputStream.close();
            answerFileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 验证答案的正确率
     */
    public static void validateAnswerFile(String exerciseFileUrl, String answerFileUrl) {
        File exerciseFile = new File(SymbolConstant.PRINT_FILE_UEL, exerciseFileUrl);
        File answerFile = new File(SymbolConstant.PRINT_FILE_UEL, answerFileUrl);
        if(exerciseFile.isFile()&&answerFile.isFile()){
            BufferedReader exerciseReader = null;
            BufferedReader answerReader = null;
            List<Integer> Correct = new ArrayList<Integer>();
            List<Integer> Wrong = new ArrayList<Integer>();
            try {
                exerciseReader= new BufferedReader(new InputStreamReader(new FileInputStream(exerciseFile)));
                answerReader = new BufferedReader(new InputStreamReader(new FileInputStream(answerFile)));
                String exerciseStr= "";
                String answerStr = "";
                int line=0;//记录行数
                while((exerciseStr=exerciseReader.readLine())!=null&&(answerStr=answerReader.readLine())!=null){
                    //获取运算式的正确答案
                    String realAnswer = CalculateUtil.getExpressValue(exerciseStr);
                    if(realAnswer.equals(answerStr)){
                        line++;
                        Correct.add(line);
                    }else{
                        line++;
                        Wrong.add(line);
                    }
                }

                //打印结果
                System.out.print("Correct:"+Correct.size()+ Arrays.asList(Correct));
                System.out.println();
                System.out.print("Wrong:"+Wrong.size()+ Arrays.asList(Wrong));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(exerciseReader!=null){
                    try {
                        exerciseReader.close();
                    } catch (IOException e) {

                    }
                }
                if(answerReader!=null){
                    try {
                        answerReader.close();
                    } catch (IOException e) {

                    }
                }

            }

        }else {
            System.out.println("文件不存在！！！");
        }
    }

}
