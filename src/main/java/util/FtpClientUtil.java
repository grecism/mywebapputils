package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.StringTokenizer;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import config.PropertieService;

/**
 * 
 *<p>Title	: FtpClientUtil</p>
 * @Description	: FTP工具类
 * @author	: admin
 * @date	: 2017年11月22日上午9:37:21
 */
public class FtpClientUtil {

    protected final Logger log = Logger.getLogger(getClass());
    private ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<FTPClient>();
    private boolean binaryTransfer = true;  
    private boolean passiveMode = false;  
    private String encoding = "UTF-8";  
    private int clientTimeout = 1000 * 30;  
  
    /** 
     * 返回一个FTPClient实例 
     *  
     * @throws Exception 
     */  
    private FTPClient getFTPClient() throws Exception {  
        if (ftpClientThreadLocal.get() != null && ftpClientThreadLocal.get().isConnected()) {  
            return ftpClientThreadLocal.get();  
        } else {  
            FTPClient ftpClient = new FTPClient(); //构造一个FtpClient实例  
            ftpClient.setControlEncoding(encoding); //设置字符集  
            connect(ftpClient); //连接到ftp服务器  
            //设置为passive模式  
            if (passiveMode) {  
                ftpClient.enterLocalPassiveMode();  
            }  
            setFileType(ftpClient); //设置文件传输类型  
            try {  
                ftpClient.setSoTimeout(clientTimeout);  
            } catch (SocketException e) {  
                throw new Exception("Set timeout error.", e);  
            }  
            ftpClientThreadLocal.set(ftpClient);  
            return ftpClient;  
        }  
    }  
  
    /** 
     * 设置文件传输类型 
     *  
     * @throws Exception 
     * @throws IOException 
     */  
    private void setFileType(FTPClient ftpClient) throws Exception {  
        try {  
            if (binaryTransfer) {  
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            } else {  
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);  
            }  
        } catch (IOException e) {  
            throw new Exception("Could not to set file type.", e);  
        }  
    }  
  
    /** 
     * 连接到ftp服务器 
     * @param ftpClient
     * @param username
     * @param password
     * @return 连接成功返回true，否则返回false 
     * @throws Exception 
     */  
    private boolean connect(FTPClient ftpClient) throws Exception {  
        try {
            String host = (String) PropertieService.properties.get("xsjf.ftp.host");
            int port = Integer.parseInt((String) PropertieService.properties.get("xsjf.ftp.port"));
            String username = (String) PropertieService.properties.get("xsjf.ftp.username");
            String password = (String) PropertieService.properties.get("xsjf.ftp.password");
            ftpClient.connect(host, port);  
            // 连接后检测返回码来校验连接是否成功  
            int reply = ftpClient.getReplyCode();  
            if (FTPReply.isPositiveCompletion(reply)) {  
                //登陆到ftp服务器  
                if (ftpClient.login(username, password)) {  
                    setFileType(ftpClient);  
                    return true;  
                }  
            } else {  
                ftpClient.disconnect();  
                throw new Exception("FTP server refused connection.");  
            }  
        } catch (IOException e) {  
            if (ftpClient.isConnected()) {  
                try {  
                    ftpClient.disconnect(); //断开连接  
                } catch (IOException e1) {  
                    throw new Exception("Could not disconnect from server.", e1);  
                }  
            }  
            throw new Exception("Could not connect to server.", e);  
        }  
        return false;  
    }  
  
    /** 
     * 断开ftp连接 
     * @throws Exception 
     */  
    private void disconnect() throws Exception {  
        try {  
            FTPClient ftpClient = getFTPClient();  
            ftpClient.logout();  
            if (ftpClient.isConnected()) {  
                ftpClient.disconnect();  
                ftpClient = null;  
            }  
        } catch (IOException e) {  
            throw new Exception("Could not disconnect from server.", e);  
        }  
    }  
      
    public boolean mkdir(String pathname) throws Exception {  
        return mkdir(pathname, null);  
    }  
      
    /** 
     * 在ftp服务器端创建目录（不支持一次创建多级目录） 
     * 该方法执行完后将自动关闭当前连接 
     * @param pathname 
     * @return 
     * @throws Exception 
     */  
    public boolean mkdir(String pathname, String workingDirectory) throws Exception {  
        return mkdir(pathname, workingDirectory, true);  
    }  
      
    /** 
     * 在ftp服务器端创建目录（不支持一次创建多级目录） 
     * @param pathname 
     * @param autoClose 是否自动关闭当前连接 
     * @throws Exception 
     */  
    public boolean mkdir(String pathname, String workingDirectory, boolean autoClose) throws Exception {  
        try {  
            getFTPClient().changeWorkingDirectory(workingDirectory);  
            return getFTPClient().makeDirectory(pathname);  
        } catch (IOException e) {  
            throw new Exception("Could not mkdir.", e);  
        } finally {  
            if (autoClose) {  
                disconnect(); //断开连接  
            }  
        }  
    }  
  
    /** 
     * 上传一个本地文件到远程指定文件 
     * @param remoteAbsoluteFile 远程文件名(包括完整路径) 
     * @param localAbsoluteFile 本地文件名(包括完整路径) 
     * @return 成功时，返回true，失败返回false 
     * @throws Exception 
     */  
    public boolean put(String remoteAbsoluteFile, String localAbsoluteFile) throws Exception {  
        return put(remoteAbsoluteFile, localAbsoluteFile, true);  
    }  
  
    /** 
     * 上传一个本地文件到远程指定文件 
     * @param remoteAbsoluteFile 远程文件名(包括完整路径) 
     * @param localAbsoluteFile 本地文件名(包括完整路径) 
     * @param autoClose 是否自动关闭当前连接 
     * @return 成功时，返回true，失败返回false 
     * @throws Exception 
     */  
    public boolean put(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose) throws Exception {  
        InputStream input = null;  
        try {  
            // 处理传输  
            input = new FileInputStream(localAbsoluteFile);  
            Boolean flag = getFTPClient().storeFile(remoteAbsoluteFile, input);  
            log.debug("put " + localAbsoluteFile);  
            return flag;  
        } catch (FileNotFoundException e) {  
            throw new Exception("local file not found.", e);  
        } catch (IOException e) {  
            throw new Exception("Could not put file to server.", e);  
        } finally {  
            try {  
                if (input != null) {  
                    input.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("Couldn't close FileInputStream.", e);  
            }  
            if (autoClose) {  
                disconnect(); //断开连接  
            }  
        }  
    }  
    
    /**
     * 储存附件开始---------------------------------------------------------
     * @param remoteFileDir
     * @param localAbsoluteFile
     * @param autoClose
     * @return
     * @throws Exception
     */
    public boolean put2(String remoteFileDir,String remoteFile, String localAbsoluteFile, boolean autoClose) throws Exception {  
        InputStream input = null;  
        try {  
            // 处理传输  
            input = new FileInputStream(localAbsoluteFile);  
            makeDirs(remoteFileDir);
            Boolean flag = getFTPClient().storeFile(remoteFileDir+"/"+remoteFile, input);  
            log.debug("put " + localAbsoluteFile);  
            return flag;  
        } catch (FileNotFoundException e) {  
            throw new Exception("local file not found.", e);  
        } catch (IOException e) {  
            throw new Exception("Could not put file to server.", e);  
        } finally {  
            try {  
                if (input != null) {  
                    input.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("Couldn't close FileInputStream.", e);  
            }  
            if (autoClose) {  
                disconnect(); //断开连接  
            }  
        }  
    }  
    
    public  boolean makeDirs(String path){
    boolean flag = true;
	  StringTokenizer s = new StringTokenizer(path, "/");
      s.countTokens(); 
      String pathName = ""; 
      while (s.hasMoreElements()) { 
      	   pathName = pathName + "/" + (String) s.nextElement(); 
      	   try { 
      		  getFTPClient().mkd(pathName); 
      	   } catch (Exception e) { 
      		   log.info("ftp文件夹创建失败");
      		   flag = false;
      	   } 
      } 
    	return flag;
    }
    
    
    /**
     * 储存附件结束--------------------------------------------------------
     */
    
    /** 
     * 下载一个远程文件到本地的指定文件 
     *  
     * @param remoteAbsoluteFile 远程文件名(包括完整路径) 
     * @param localAbsoluteFile 本地文件名(包括完整路径) 
     * @return 成功时，返回true，失败返回false 
     * @throws Exception 
     */  
    public boolean get(String remoteAbsoluteFile, String localAbsoluteFile) throws Exception {  
        return get(remoteAbsoluteFile, localAbsoluteFile, true);  
    }  
  
    /** 
     * 下载一个远程文件到本地的指定文件 
     * @param remoteAbsoluteFile 远程文件名(包括完整路径) 
     * @param localAbsoluteFile 本地文件名(包括完整路径) 
     * @param autoClose 是否自动关闭当前连接 
     * @return 成功时，返回true，失败返回false 
     * @throws Exception 
     */  
    public boolean get(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose) throws Exception {  
        OutputStream output = null;  
        try {  
            output = new FileOutputStream(localAbsoluteFile);  
            return get(remoteAbsoluteFile, output, autoClose);  
        } catch (FileNotFoundException e) {  
            throw new Exception("local file not found.", e);  
        } finally {  
            try {  
                if (output != null) {  
                    output.close();  
                }  
            } catch (IOException e) {  
                throw new Exception("Couldn't close FileOutputStream.", e);  
            }  
        }  
    }  
  
    /** 
     * 下载一个远程文件到指定的流 处理完后记得关闭流 
     * @param remoteAbsoluteFile 
     * @param output 
     * @return 
     * @throws Exception 
     */  
    public boolean get(String remoteAbsoluteFile, OutputStream output) throws Exception {  
        return get(remoteAbsoluteFile, output, true);  
    }  
  
    /** 
     * 下载一个远程文件到指定的流 处理完后记得关闭流 
     * @param remoteAbsoluteFile 
     * @param output 
     * @param delFile 
     * @return 
     * @throws Exception 
     */  
    public boolean get(String remoteAbsoluteFile, OutputStream output, boolean autoClose) throws Exception {  
        try {  
            FTPClient ftpClient = getFTPClient();  
            // 处理传输  
            return ftpClient.retrieveFile(remoteAbsoluteFile, output);  
        } catch (IOException e) {  
            throw new Exception("Couldn't get file from server.", e);  
        } finally {  
            if (autoClose) {  
                disconnect(); //关闭链接  
            }  
        }  
    }  
  
    /** 
     * 从ftp服务器上删除一个文件 
     * 该方法将自动关闭当前连接 
     * @param delFile 
     * @return 
     * @throws Exception 
     */  
    public boolean delete(String delFile) throws Exception {  
        return delete(delFile, true);  
    }  
      
    /** 
     * 从ftp服务器上删除一个文件 
     * @param delFile 
     * @param autoClose 是否自动关闭当前连接 
     * @return 
     * @throws Exception 
     */  
    public boolean delete(String delFile, boolean autoClose) throws Exception {  
        try {  
            getFTPClient().deleteFile(delFile);  
            return true;  
        } catch (IOException e) {  
            throw new Exception("Couldn't delete file from server.", e);  
        } finally {  
            if (autoClose) {  
                disconnect(); //关闭链接  
            }  
        }  
    }  
      
    /** 
     * 批量删除 
     * 该方法将自动关闭当前连接 
     * @param delFiles 
     * @return 
     * @throws Exception 
     */  
    public boolean delete(String[] delFiles) throws Exception {  
        return delete(delFiles, true);  
    }  
  
    /** 
     * 批量删除 
     * @param delFiles 
     * @param autoClose 是否自动关闭当前连接 
     * @return 
     * @throws Exception 
     */  
    public boolean delete(String[] delFiles, boolean autoClose) throws Exception {  
        try {  
            FTPClient ftpClient = getFTPClient();  
            for (String s : delFiles) {  
                ftpClient.deleteFile(s);  
            }  
            return true;  
        } catch (IOException e) {  
            throw new Exception("Couldn't delete file from server.", e);  
        } finally {  
            if (autoClose) {  
                disconnect(); //关闭链接  
            }  
        }  
    }  
  
    /** 
     * 列出远程默认目录下所有的文件 
     * @return 远程默认目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组 
     * @throws Exception 
     */  
    public String[] listNames() throws Exception {  
        return listNames(null, true);  
    }  
      
    public String[] listNames(boolean autoClose) throws Exception {  
        return listNames(null, autoClose);  
    }  
  
    /** 
     * 列出远程目录下所有的文件 
     * @param remotePath 远程目录名 
     * @param autoClose 是否自动关闭当前连接 
     * @return 远程目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组 
     * @throws Exception 
     */  
    public String[] listNames(String remotePath, boolean autoClose) throws Exception {  
        try {  
            String[] listNames = getFTPClient().listNames(remotePath);  
            return listNames;  
        } catch (IOException e) {  
            throw new Exception("列出远程目录下所有的文件时出现异常", e);  
        } finally {  
            if (autoClose) {  
                disconnect(); //关闭链接  
            }  
        }  
    }  
    
    /** 判断Ftp目录是否存在 */
	public boolean isDirExist( String dir){
		return true;
    }
  
    public static void main(String[] args) throws Exception, InterruptedException {  
        FtpClientUtil ftp = new FtpClientUtil();  
//        ftp.setHost("localhost");  
//        ftp.setPort(2121);  
//        ftp.setUsername("admin");  
//        ftp.setPassword("admin");  
//        ftp.setBinaryTransfer(false);  
//        ftp.setPassiveMode(false);  
//        ftp.setEncoding("utf-8");  
  
        //boolean ret = ftp.put("/group/tbdev/query/user-upload/12345678910.txt", "D:/099_temp/query/12345.txt");  
        //System.out.println(ret);  
        ftp.mkdir("asd", "user-upload");  
          
        //ftp.disconnect();  
        //ftp.mkdir("user-upload1");  
        //ftp.disconnect();  
          
        //String[] aa = {"/group/tbdev/query/user-upload/123.txt", "/group/tbdev/query/user-upload/SMTrace.txt"};  
        //ftp.delete(aa);  
    }
    
}

