package net.ktds.drink.support;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * JSP/Servlet Í∏∞Î∞ò?ùò Project?óê?Ñú ?åå?ùº?ùÑ Upload ?ï† ?àò ?ûà?äî Utility
 * commons-fileuploadÎ•? ?Ç¨?ö©?ï®.
 * @author Minchang Jang (mc.jang@hucloud.co.kr)
 *
 */
public class MultipartHttpServletRequest {

	private HttpServletRequest request;
	private List<FileItem> items;
	
	/**
	 * ?ùºÎ∞òÏ†Å?ù∏ HttpServletReqeustÎ•? MultipartHttpServletRequestÎ°? Î≥??ôò?ïú?ã§.
	 * @param request
	 */
	public MultipartHttpServletRequest(HttpServletRequest request) {
		this.request = request;
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = request.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8"); 
		
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * request?óê?Ñú ParameterÎ•? Í∞??†∏?ò®?ã§.
	 * @param name
	 * @return
	 */
	public String getParameter(String name) {
		for ( FileItem fileItem : items ) {
			if ( fileItem.getFieldName().equals(name) )  {
				try {
					return fileItem.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					return fileItem.getString();
				}
			}
		}
		return null;
	}
	
	/**
	 * request?óê?Ñú ParameterÎ•? Í∞??†∏?ò®?ã§.
	 * @param name
	 * @return
	 */
	public List<String> getParameterValue(String name) {
		List<String> values = new ArrayList<String>();
		
		for ( FileItem fileItem : items ) {
			if ( fileItem.getFieldName().equals(name) )  {
				try {
					values.add(fileItem.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					values.add(fileItem.getString());
				}
			}
		}
		return values;
	}
	
	/**
	 * Session?ùÑ ?ñª?ñ¥?ò®?ã§.
	 * @return
	 */
	public HttpSession getSession() {
		return request.getSession();
	}
	
	/**
	 * RequestDispatcherÎ•? ?ñª?ñ¥?ò®?ã§.
	 * @param jspPage
	 * @return
	 */
	public RequestDispatcher getRequestDispatcher(String jspPage) {
		return request.getRequestDispatcher(jspPage);
	}
	
	/**
	 * ?óÖÎ°úÎìú?ïú File ParameterÎ•? ?ñª?ñ¥?ò®?ã§.
	 * @param name
	 * @return
	 */
	public MultipartFile getFile(String name) {
		MultipartFile file = new MultipartFile();
		
		for ( FileItem fileItem : items ) {
			if ( fileItem.getFieldName().equals(name) )  {
				file.setFileName(fileItem.getName());
				file.setFileSize(fileItem.getSize());
				file.setContentType(fileItem.getContentType());
				file.setFileItem(fileItem);
				
				return file;
			}
		}
		
		return null;
	}
	
	/**
	 * Upload ?ïú ?åå?ùº?ù¥ ?ûÑ?ãú?†Å?úºÎ°? ???û•?ê† ?Å¥?ûò?ä§
	 * @author Minchang Jang (mc.jang@hucloud.co.kr)
	 *
	 */
	public class MultipartFile {
		private String fileName;
		private long fileSize;
		private String contentType;
		private FileItem fileItem;
		
		/**
		 * ?åå?ùº?ùò ?ù¥Î¶ÑÏùÑ Í∞??†∏?ò®?ã§.
		 * @return
		 */
		public String getFileName() {
			return fileName;
		}
		/**
		 * ?åå?ùº?ùò ?ù¥Î¶ÑÏùÑ ?Ñ§?†ï?ïú?ã§.
		 * @param fileName
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * ?åå?ùº?ùò ?Å¨Í∏∞Î?? Í∞??†∏?ò®?ã§.
		 * @return
		 */
		public long getFileSize() {
			return fileSize;
		}
		/**
		 * ?åå?ùº?ùò ?Å¨Í∏∞Î?? ?Ñ§?†ï?ïú?ã§.
		 * @param fileSize
		 */
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}

		/**
		 * ?åå?ùº?ùò Type?ùÑ Í∞??†∏?ò®?ã§.
		 * @return
		 */
		public String getContentType() {
			return contentType;
		}
		/**
		 * ?åå?ùº?ùò Type?ùÑ ?Ñ§?†ï?ïú?ã§.
		 * @param contentType
		 */
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		
		/**
		 * Upload ?ïú File?ù¥ ?ûÑ?ãú?†Å?úºÎ°? ???û•?êò?äî FileItem(commons-fileupload ?†Ñ?ö©)
		 * @param fileItem
		 */
		public void setFileItem(FileItem fileItem) {
			this.fileItem = fileItem;
		}
		
		/**
		 * Upload ?ïú ?åå?ùº?ùÑ Ïß??†ï?ïú ?û•?Üå?óê ???û•?ïú?ã§.
		 * @param dest
		 * @return
		 */
		public File write(String dest) {
			File file = new File(dest);
			try {
				fileItem.write(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return file;
		}
		
	}
	
}


