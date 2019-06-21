package multi_thread_read;

import java.io.File;
import java.util.Stack;

public class GetFileLists {
    public static void main(String[] args) {
        String location = "D:/excel";
        File dir = new File(location);
        File[] files = dir.listFiles();

        Stack<String> stack = new Stack<String>();

        if (files != null){
            for (File file: files) {
                //入栈
                stack.add(file.toString());

                //循环输出文件名和文件的绝对路径
                System.out.println(file.getName()+"····"+file.getAbsolutePath());
            }
            for(;;){
                if (stack.isEmpty()){
                    System.out.println("栈已空·····");
                    return;
                }
                System.out.println(stack.pop());
            }
        }
    }

}
