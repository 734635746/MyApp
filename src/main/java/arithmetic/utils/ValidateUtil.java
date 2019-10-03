package arithmetic.utils;

import arithmetic.constant.SymbolConstant;

import java.io.File;

/**
 * @author jingshanccc
 * @description: 各种检查
 * @create: 2019-10-03 19:01
 */
public class ValidateUtil {

    /*
     * @Description: 参数检查 只有两种合法输入
     * @param command
     * @return: 返回定好顺序的命令数组
     */
    public static String[] checkParams(String command) {
        String[] s = command.split(" ");
        //其实只能输入长度为4的指令
        if (s.length == 4){
            String first = s[0];String third = s[2];String second = s[1];String fourth = s[3];
            //1. -n i -r j / -r i -n j
            if((first.equals("-n")&&third.equals("-r")) || (first.equals("-e")&&third.equals("-a"))){
                return s;
            }
            if((first.equals("-r")&&third.equals("-n")) || (first.equals("-a")&&third.equals("-e"))){
                s[0] = third;
                s[1] = fourth;
                s[2] = first;
                s[3] = second;
                return s;
            }
            return null;
        }else {
            return null;
        }
    }

    /*
     * @Description: 对绝对路径和相对路径的支持
     * @param path
     * @return: 正确路径
     */
    public static String improvePath(String path) {
        if (path.contains("/")){//替换分隔符
            path = path.replace("/", File.separator);
        }
        String p = "";
        if (path.indexOf(":") > 0) {//绝对路径
            p = path;
        } else {//相对路径
            p = SymbolConstant.PRINT_FILE_URL + File.separator + path;
        }
        return p;
    }
}
