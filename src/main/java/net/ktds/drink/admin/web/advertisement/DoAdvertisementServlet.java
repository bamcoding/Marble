package net.ktds.drink.admin.web.advertisement;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;

public class DoAdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

	public DoAdvertisementServlet() {
		super();
		adminBiz = new AdminBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fileName="";
		 MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		String contractDate = multipartRequest.getParameter("contractDate");
		String expirationDate = multipartRequest.getParameter("expirationDate");
		String advertisementId = multipartRequest.getParameter("advertisementId");
		System.out.println(advertisementId);
		if( contractDate.length() == 0){
			response.sendRedirect("/Marble/admin/doAdvertisement?errorCode=1");
			return;
		}
		if(expirationDate.length() == 0){
			response.sendRedirect("/Marble/admin/doAdvertisement?errorCode=1");
			return;
		}
		
		MultipartFile uploadFile = multipartRequest.getFile("file");
		
		 if ( uploadFile.getFileSize() > 0 ) {
	            
	            File uploadFileDirectory = new File("D:\\marble\\uploadfiles\\");
	            
	            // exists() -> 있다면
	            if( !uploadFileDirectory.exists() ) {
	                uploadFileDirectory.mkdirs();
	            }
	            uploadFile.write("D:\\marble\\uploadfiles\\" + uploadFile.getFileName());
	            fileName = uploadFile.getFileName();
	        }

		AdvertisementVO advertisement = new AdvertisementVO();
		advertisement.setFileName(fileName);
		advertisement.setContractDate(contractDate);
		advertisement.setExpirationDate(expirationDate);
		advertisement.setFilePath("10.225.152.174:8080/Marble/admin/advertisement?advertisementId="+advertisementId);
		boolean isSuccess = adminBiz.addAdvertisement(advertisement);
		if (isSuccess){
			
			response.sendRedirect("/Marble/admin/advertisement");
			
		}
		else{
			
			response.sendRedirect("/Marble/admin/doAdvertisement?errorcode=2");
			
		}
	
	}

}
