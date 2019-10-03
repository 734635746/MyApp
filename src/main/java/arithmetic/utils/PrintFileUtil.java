package arithmetic.utils;

import arithmetic.constant.SymbolConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/28 - 19:05
 * 生成文件的工具类
 */
public class PrintFileUtil {

    /**
     * 根据运算式子生成练习文件和答案文件
     *
     * @param express
     */
    public static void printExerciseFileAndAnswerFile(String[] express) {
        File dir = new File(SymbolConstant.PRINT_FILE_URL);
        if (!dir.exists()) { //解决FileNotFound
            dir.mkdir();
        }
        File exerciseFile = new File(SymbolConstant.PRINT_FILE_URL, "Exercises.txt");
        File answerFile = new File(SymbolConstant.PRINT_FILE_URL, "Answers.txt");
        try {

            OutputStream exerciseFileOutputStream = new FileOutputStream(exerciseFile);
            OutputStream answerFileOutputStream = new FileOutputStream(answerFile);
            StringBuilder exerciseBuffer = new StringBuilder();
            StringBuilder answerFileBuffer = new StringBuilder();
            for (String exp : express) {
                exerciseBuffer.append(exp).append("\r\n");
                answerFileBuffer.append(CalculateUtil.getExpressValue(exp)).append("\r\n");
            }
            exerciseFileOutputStream.write(exerciseBuffer.toString().getBytes());
            answerFileOutputStream.write(answerFileBuffer.toString().getBytes());
            exerciseFileOutputStream.close();
            answerFileOutputStream.close();
            System.out.println("操作成功！！！");
        }
        //catch (FileNotFoundException e) {
        //    //e.printStackTrace();
        //    System.out.println("找不到文件，请重试");
        //}
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println("文件操作异常，请重试");
        }


    }

    /**
     * 验证答案的正确率
     */
    public static void validateAnswerFile(String exerciseFileUrl, String answerFileUrl) {
        File exerciseFile = new File(ValidateUtil.improvePath(exerciseFileUrl));//SymbolConstant.PRINT_FILE_URL, exerciseFileUrl);
        File answerFile = new File(ValidateUtil.improvePath(answerFileUrl));
        File gradeFile = new File(SymbolConstant.PRINT_FILE_URL, "Grade.txt");
        if (exerciseFile.isFile() && answerFile.isFile()) {
            BufferedReader exerciseReader = null;
            BufferedReader answerReader = null;
            OutputStream gradeFileOutputStream = null;
            List<Integer> Correct = new ArrayList<Integer>();
            List<Integer> Wrong = new ArrayList<Integer>();
            try {
                exerciseReader = new BufferedReader(new InputStreamReader(new FileInputStream(exerciseFile)));
                answerReader = new BufferedReader(new InputStreamReader(new FileInputStream(answerFile)));
                String exerciseStr = "";
                String answerStr = "";
                int line = 0;//记录行数
                while ((exerciseStr = exerciseReader.readLine()) != null && (answerStr = answerReader.readLine()) != null) {
                    //获取运算式的正确答案
                    String realAnswer = CalculateUtil.getExpressValue(exerciseStr);
                    if (realAnswer.equals(answerStr)) {
                        line++;
                        Correct.add(line);
                    } else {
                        line++;
                        Wrong.add(line);
                    }
                }
                String result = "Correct:" + Correct.size() + Collections.singletonList(Correct) + "\r\n" + "Wrong:" + Wrong.size() + Collections.singletonList(Wrong);
                //保存成绩文件
                gradeFileOutputStream = new FileOutputStream(gradeFile);
                gradeFileOutputStream.write(result.getBytes());
                //打印结果
                System.out.print(result);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (exerciseReader != null) {
                    try {
                        exerciseReader.close();
                    } catch (IOException ignored) {
                    }
                }
                if (answerReader != null) {
                    try {
                        answerReader.close();
                    } catch (IOException ignored) {
                    }
                }
                if (gradeFileOutputStream != null) {
                    try {
                        gradeFileOutputStream.close();
                    } catch (IOException ignored) {
                    }
                }
            }

        } else {
            System.out.println("文件不存在！！！");
        }
    }

}
