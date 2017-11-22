package util;

import org.apache.commons.lang.time.DateFormatUtils;

import config.ConfigFile;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ReportUtil {
   
	/**
	 * 
	 * @Description : json写入文件
	 * @param configPath
	 * @param sets
	 * @param id
	 * @return
	 * @throws IOException
	 */
    public static String writeFile(String configPath, String sets,Integer id) throws IOException {
        if (ConfigFile.ISDEMO.endsWith("/") || ConfigFile.ISDEMO.endsWith("\\")) {
            configPath = configPath.substring(0, configPath.length() - 1);
        }
        Calendar c = Calendar.getInstance();
        StringBuffer realPath = new StringBuffer();
        realPath.append(File.separator).append(c.get(Calendar.YEAR))
                .append(File.separator).append((c.get(Calendar.MONTH) + 1)).append(File.separator)
                .append(c.get(Calendar.DAY_OF_MONTH));
        // 判断目录是否存在，不存在，则创建
        File dir = new File(configPath + realPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        realPath.append(File.separator).append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")).append("-").append(id).append("-").append(uuid).append(".").append("json");
        File file = new File(configPath + realPath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        writer.write(sets);
        writer.flush();
        writer.close();
        return realPath.toString();
    }

    /**
     * 
     * @Description : 修改文件中json
     * @param path
     * @param content
     * @return
     * @throws IOException
     */
    public static String updateFile(String path, String content) throws IOException {
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        writer.write(content);
        writer.flush();
        writer.close();
        return path;
    }


   /**
    * 
    * @Description : 读取json文件
    * @param path
    * @return
    * @throws IOException
    */
    public static String ReadFile(String path) throws IOException {
        String content = "";
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            content += line + "\n";
        }
        reader.close();
        return content;
    }
}
