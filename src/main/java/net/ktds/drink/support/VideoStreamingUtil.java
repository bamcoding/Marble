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
 * ?��?�� ?��?��로드�? ?��?��?���? ?��.
 * Internet Explorer, Mozilia 모두 ?��?��
 * @author Minchang Jang (mc.jang@hucloud.co.kr)
 *
 */
public class VideoStreamingUtil {

	private String uploadPath;
	
	/**
	 * ?��?��?�� ?��로드 ?��?�� ?��?�� 경로�? �??��?��.
	 * @return
	 */
	public String getUploadPath() {
		return uploadPath;
	}
	/**
	 * ?��?��?�� ?��로드 ?��?�� ?��?�� 경로�? �??��.
	 * @param uploadPath
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	private static VideoStreamingUtil downloadUtil;
	
	private VideoStreamingUtil() {}
	
	/**
	 * DownloadUtil ?��?��?��?���? �??��?��.
	 * @param filePath ?��?��로드 ?��?��?�� ?��?��?�� ?��치한 ?��버상?�� 물리?��?�� ?��?? 경로
	 * @return
	 */
	public static VideoStreamingUtil getInstance(String filePath) {
		
		if ( downloadUtil == null ) {
			downloadUtil = new VideoStreamingUtil();
		}
		
		downloadUtil.setUploadPath(filePath);
		
		return downloadUtil;
	}
	
	/**
	 * ?��?��?�� ?��?��로드 ?��.
	 * @param request
	 * @param response
	 * @param realFileName ?��버에 존재?��?�� 물리?��?�� ?��?��?�� ?���?
	 * @param displayFileName ?��?��로드 ?�� ?�� ?��?��?��?���? 보여�? ?��?��?�� ?���?
	 * @throws UnsupportedEncodingException
	 */
	public void download(HttpServletRequest request,
						HttpServletResponse response,
						String realFileName,
						String displayFileName) throws UnsupportedEncodingException {
		
		File downloadFile = new File(this.getUploadPath() + File.separator + realFileName);
		
		response.setContentType("video/mp4; charset=utf-8");
		response.setContentLength( (int) downloadFile.length());
		
		// ?��?��?��?�� 브라?��?�� ?��보�?? �??��?��?��.
		String userAgent = request.getHeader("User-Agent");
		// ?��?��?��?�� 브라?��??�? MicroSoft Internet Explorer ?���? ?��?��?��?��.
		boolean internetExplorer = userAgent.indexOf("MSIE") > -1;
		if( !internetExplorer ) {
			internetExplorer = userAgent.indexOf("Gecko") > -1;
		}
		
		// ?��?��로드?�� ?��?��?�� ?��름을 브라?��?��별로 �??��?��?��.
		String fileName = new String(displayFileName.getBytes(), "UTF-8");
		if ( internetExplorer ) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		}
		else {
			// File?�� ?��름을 UTF-8 ???��?��?�� ISO-8859-1 ???��?���? �?경한?��.
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		// 브라?��?���? 받을 ?��?��?�� ?��름을 response?�� ?��록한?��.
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\";");
		// 브라?��?���? ?��?��로드 받�? ?�� Binary ?��?���? ?��?��?��?���? 보낸?��.
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
