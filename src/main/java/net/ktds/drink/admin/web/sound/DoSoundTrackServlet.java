package net.ktds.drink.admin.web.sound;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SoundTrackVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;

public class DoSoundTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

    public DoSoundTrackServlet() {
        super();
        adminBiz = new AdminBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName="";
		 MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		MultipartFile uploadFile = multipartRequest.getFile("file");
		
		 if ( uploadFile.getFileSize() > 0 ) {
	            
	            File uploadFileDirectory = new File("D:\\board\\uploadfiles\\");
	            
	            // exists() -> 있다면
	            if( !uploadFileDirectory.exists() ) {
	                uploadFileDirectory.mkdirs();
	            }
	            uploadFile.write("D:\\board\\uploadfiles\\" + uploadFile.getFileName());
	            fileName = uploadFile.getFileName();
	        }
		 else{
			 response.sendRedirect("/Marble/admin/soundTrackId?errorCode=1");
		 }
		

		SoundTrackVO soundTrack = new SoundTrackVO();
		soundTrack.setFileName(fileName);
		boolean isSuccess = adminBiz.addSoundTrack(soundTrack);
		if (isSuccess){
			response.sendRedirect("/Marble/admin/soundTrack");
		}
		else{
			response.sendRedirect("/Marble/admin/soundTrack?errorcode=2");
		}
	
	}

}
