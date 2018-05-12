import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateKeyProblem {
    public static void main(String[] args) {
        String[] sb = new String[2];
        sb[0] = new String("messages_en");
        sb[1] = new String("messages_zh");
        for(int i= 0; i < sb.length; i++)  readAndWrite(sb[i]);
    }
    
    /**
     * update the lowwer case in lineData to upper case
     * @param lineData
     * @return updated lineData
     */
    public static String updateupperCase(String lineData) {
        String result = "";
        if (lineData.contains("=")) {
            String[] str = lineData.split("=");
            result = str[0].toLowerCase()+"="+str[1];
            System.out.println(result);
        } else {
            result = lineData;
        }
        return result;
    }
    
    /**
     * read the file needing update and write the new data in new file
     * @param fileName
     */
    public static void readAndWrite(String fileName) {
        String fileNeedUpdate = fileName + ".properties";
        String fileNeedWrite = fileName + ".txt";
        StringBuffer sb = new StringBuffer("");
        FileReader reader = null;//按字符读取流中数据
        BufferedReader br = null;//BufferedReader的readLine()方法是阻塞式的, 如果到达流末尾, 就返回null
        File newFile = null;//用于新建文件
        FileWriter writer = null;
        BufferedWriter bw = null;//与FileWriter连用，
        try {
            reader = new FileReader(fileNeedUpdate);
            newFile = new File(fileNeedWrite);
            writer = new FileWriter(newFile);
            bw = new BufferedWriter(writer);
            br = new BufferedReader(reader);
            String strReadOut = null;
            strReadOut = br.readLine();
            while ((strReadOut) != null) {
                bw.write(updateupperCase(strReadOut));
                bw.newLine();
                strReadOut = br.readLine();
            }
        } catch (IOException e) {//捕捉上述操作中的IO异常
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                br.close();
                bw.close();
                writer.close();
            } catch (IOException e) {//捕捉上述操作中的IO异常
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }  
    }
}
