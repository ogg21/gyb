package com.gyb.utils.file.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.gyb.utils.PropertiesUtil;



public class FtpApche {

	private static FTPClient ftpClient = new FTPClient();
	private static String encoding = System.getProperty( "file.encoding");
	/**
	     * Description: 向FTP服务器上传文件
	     *
	     * @Version1.0
	     * @param url
	     *            FTP服务器hostname
	     * @param port
	     *            FTP服务器端口
	     * @param username
	     *            FTP登录账号
	     * @param password
	     *            FTP登录密码
	     * @param path
	     *            FTP服务器保存目录,如果是根目录则为“/”
	     * @param filename
	     *            上传到FTP服务器上的文件名
	     * @param input
	     *            本地文件输入流
	     * @return 成功返回true，否则返回false
	     */
	private static final String url = PropertiesUtil.getString("ftp_host");
	private static final int port = Integer.parseInt(PropertiesUtil.getString("ftp_port"));
	private static final String username = PropertiesUtil.getString("ftp_user");
	private static final String password = PropertiesUtil.getString("ftp_password");
	private static final String  rootPath = PropertiesUtil.getString("ftp_root");  //"/wwwroot/ftpuser/";
	public static boolean uploadFile(String fileFolder,String filename, InputStream inputStream) throws Exception{
		boolean result = false;
		try {
			int reply;
			ftpClient.connect( url,port);
			ftpClient.login( username, password);
			//ftpClient.setControlEncoding( encoding);
			//检测登录成功与否
			reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				System.out.println("连接"+url+"失败...");
				ftpClient.disconnect();
				return result;
			}
			// 看reply返回的值是不是230，如果是，表示登陆成功
			if(reply==230){
				System.out.println("connection"+url+"success ...");
			}
			ftpClient.setFileType( FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			//ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			
			
			//String checkPath = rootPath  + File.separator +  fileFolder + File.separator;
			String checkPath = rootPath  +  fileFolder;
			System.out.println( "file will be stored in" + checkPath);
			boolean changetoFtpFolder = ftpClient.changeWorkingDirectory(checkPath);
			
			if(changetoFtpFolder){
				System.out.println("file is storing...");
				result = ftpClient.storeFile(filename, inputStream);
				inputStream.close();
			}else{
				System.out.println( fileFolder + "is not exist,creating...");
				ftpClient.changeWorkingDirectory(rootPath+File.separator);
				ftpClient.makeDirectory(fileFolder);
				ftpClient.changeWorkingDirectory(rootPath+File.separator + fileFolder + File.separator);
				System.out.println( "change folder in" + rootPath+File.separator + fileFolder + File.separator);
				result = ftpClient.storeFile(filename, inputStream);
				inputStream.close();
				
			}
			//result = ftpClient.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), inputStream);
			ftpClient.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ftpClient.isConnected()){
				ftpClient.disconnect();
			}
		}
		return result ;
	}
	
	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream( new File("d:/logo1234.png"));
			//注意FTP用户权限只能访问到/wwwroot/ftpuser/下
			boolean upload = uploadFile( "", "kkkkkkk123.png", inputStream);
			System.out.println( upload);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
