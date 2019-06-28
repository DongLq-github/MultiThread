import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileOperate {

    List<File> list = new ArrayList<File>();
    File dir = new File("D:/excel/log");

    @Test//删除文件
    public void deleteFile(){
        findFiles(dir);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("log_8000.xls")){
                list.get(i).delete();
            }
        }
    }

    /**
     * 递归查找文件
     * @param dir
     */
    public void findFiles(File dir){
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()){
                findFiles(files[i]);
            }else {
                list.add(files[i]);
            }
        }
    }

}
