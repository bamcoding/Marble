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
 * ?ŒŒ?¼ ?‹¤?š´ë¡œë“œë¥? ?œ ?š©?•˜ê²? ?•¨.
 * Internet Explorer, Mozilia ëª¨ë‘ ?˜¸?™˜
 * @author Minchang Jang (mc.jang@hucloud.co.kr)
 *
 */
public class DownloadUtil {

	private String uploadPath;
	
	/**
	 * ?ŒŒ?¼?´ ?—…ë¡œë“œ ?˜?–´ ?ˆ?Š” ê²½ë¡œë¥? ê°?? ¸?˜´.
	 * @return
	 */
	public String getUploadPath() {
		return uploadPath;
	}
	/**
	 * ?ŒŒ?¼?´ ?—…ë¡œë“œ ?˜?–´ ?ˆ?Š” ê²½ë¡œë¥? ì§?? •.
	 * @param uploadPath
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	private static DownloadUtil downloadUtil;
	
	private DownloadUtil() {}
	
	/**
	 * DownloadUtil ?¸?Š¤?„´?Š¤ë¥? ê°?? ¸?˜´.
	 * @param filePath ?‹¤?š´ë¡œë“œ ?•˜? ¤?Š” ?ŒŒ?¼?´ ?œ„ì¹˜í•œ ?„œë²„ìƒ?˜ ë¬¼ë¦¬? ?¸ ? ˆ?? ê²½ë¡œ
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
	 * ?ŒŒ?¼?„ ?‹¤?š´ë¡œë“œ ?•¨.
	 * @param request
	 * @param response
	 * @param realFileName ?„œë²„ì— ì¡´ì¬?•˜?Š” ë¬¼ë¦¬? ?¸ ?ŒŒ?¼?˜ ?´ë¦?
	 * @param displayFileName ?‹¤?š´ë¡œë“œ ?•  ?•Œ ?‚¬?š©??—ê²? ë³´ì—¬ì§? ?ŒŒ?¼?˜ ?´ë¦?
	 * @throws UnsupportedEncodingException
	 */
	public void download(HttpServletRequest request,
						HttpServletResponse response,
						String realFileName,
						String displayFileName) throws UnsupportedEncodingException {
		
		File downloadFile = new File(this.getUploadPath() + File.separator + realFileName);
		
		response.setContentType("application/download; charset=utf-8");
		response.setContentLength( (int) downloadFile.length());
		
		// ?‚¬?š©??˜ ë¸Œë¼?š°? ¸ ? •ë³´ë?? ê°?? ¸?˜¨?‹¤.
		String userAgent = request.getHeader("User-Agent");
		// ?‚¬?š©??˜ ë¸Œë¼?š°??ê°? MicroSoft Internet Explorer ?¸ì§? ?™•?¸?•œ?‹¤.
		boolean internetExplorer = userAgent.indexOf("MSIE") > -1;
		if( !internetExplorer ) {
			internetExplorer = userAgent.indexOf("Gecko") > -1;
		}
		
		// ?‹¤?š´ë¡œë“œ?•  ?ŒŒ?¼?˜ ?´ë¦„ì„ ë¸Œë¼?š°? ¸ë³„ë¡œ ê°?? ¸?˜¨?‹¤.
		String fileName = new String(displayFileName.getBytes(), "UTF-8");
		if ( internetExplorer ) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		}
		else {
			// File?˜ ?´ë¦„ì„ UTF-8 ???…?—?„œ ISO-8859-1 ???…?œ¼ë¡? ë³?ê²½í•œ?‹¤.
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// ë¸Œë¼?š°? ¸ê°? ë°›ì„ ?ŒŒ?¼?˜ ?´ë¦„ì„ response?— ?“±ë¡í•œ?‹¤.
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\";");
		// ë¸Œë¼?š°? ¸ê°? ?‹¤?š´ë¡œë“œ ë°›ì? ?›„ Binary ?ŒŒ?¼ë¡? ?ƒ?„±?•˜?¼ê³? ë³´ë‚¸?‹¤.
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
