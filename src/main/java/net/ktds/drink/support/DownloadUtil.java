package net.ktds.drink.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ??Ό ?€?΄λ‘λλ₯? ? ?©?κ²? ?¨.
 * Internet Explorer, Mozilia λͺ¨λ ?Έ?
 * @author Minchang Jang (mc.jang@hucloud.co.kr)
 *
 */
public class DownloadUtil {

	private String uploadPath;
	
	/**
	 * ??Ό?΄ ?λ‘λ ??΄ ?? κ²½λ‘λ₯? κ°?? Έ?΄.
	 * @return
	 */
	public String getUploadPath() {
		return uploadPath;
	}
	/**
	 * ??Ό?΄ ?λ‘λ ??΄ ?? κ²½λ‘λ₯? μ§?? .
	 * @param uploadPath
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	private static DownloadUtil downloadUtil;
	
	private DownloadUtil() {}
	
	/**
	 * DownloadUtil ?Έ?€?΄?€λ₯? κ°?? Έ?΄.
	 * @param filePath ?€?΄λ‘λ ?? €? ??Ό?΄ ?μΉν ?λ²μ? λ¬Όλ¦¬? ?Έ ? ?? κ²½λ‘
	 * @return
	 */
	public static DownloadUtil getInstance(String filePath) {
		
		if ( downloadUtil == null ) {
			downloadUtil = new DownloadUtil();
		}
		
		downloadUtil.setUploadPath(filePath);
		
		return downloadUtil;
	}
	
	/**
	 * ??Ό? ?€?΄λ‘λ ?¨.
	 * @param request
	 * @param response
	 * @param realFileName ?λ²μ μ‘΄μ¬?? λ¬Όλ¦¬? ?Έ ??Ό? ?΄λ¦?
	 * @param displayFileName ?€?΄λ‘λ ?  ? ?¬?©??κ²? λ³΄μ¬μ§? ??Ό? ?΄λ¦?
	 * @throws UnsupportedEncodingException
	 */
	public void download(HttpServletRequest request,
						HttpServletResponse response,
						String realFileName,
						String displayFileName) throws UnsupportedEncodingException {
		
		File downloadFile = new File(this.getUploadPath() + File.separator + realFileName);
		
		response.setContentType("application/download; charset=utf-8");
		response.setContentLength( (int) downloadFile.length());
		
		// ?¬?©?? λΈλΌ?°? Έ ? λ³΄λ?? κ°?? Έ?¨?€.
		String userAgent = request.getHeader("User-Agent");
		// ?¬?©?? λΈλΌ?°??κ°? MicroSoft Internet Explorer ?Έμ§? ??Έ??€.
		boolean internetExplorer = userAgent.indexOf("MSIE") > -1;
		if( !internetExplorer ) {
			internetExplorer = userAgent.indexOf("Gecko") > -1;
		}
		
		// ?€?΄λ‘λ?  ??Ό? ?΄λ¦μ λΈλΌ?°? Έλ³λ‘ κ°?? Έ?¨?€.
		String fileName = new String(displayFileName.getBytes(), "UTF-8");
		if ( internetExplorer ) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		}
		else {
			// File? ?΄λ¦μ UTF-8 ????? ISO-8859-1 ????Όλ‘? λ³?κ²½ν?€.
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// λΈλΌ?°? Έκ°? λ°μ ??Ό? ?΄λ¦μ response? ?±λ‘ν?€.
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\";");
		// λΈλΌ?°? Έκ°? ?€?΄λ‘λ λ°μ? ? Binary ??Όλ‘? ??±??Όκ³? λ³΄λΈ?€.
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileInputStream fin = null;
		FileChannel inputChannel = null;
		WritableByteChannel outputChannel = null;
		
		try {
			fin = new FileInputStream(downloadFile);
			inputChannel = fin.getChannel();

			outputChannel = Channels.newChannel(response.getOutputStream());
			inputChannel.transferTo(0, fin.available(), outputChannel);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (outputChannel.isOpen())
					outputChannel.close();
			} catch (Exception e) {}
			try {
				if (inputChannel.isOpen())
					inputChannel.close();
			} catch (Exception e) {}
			try {
				if (fin != null)
					fin.close();
			} catch (Exception e) {}
		}
	}
	
}
