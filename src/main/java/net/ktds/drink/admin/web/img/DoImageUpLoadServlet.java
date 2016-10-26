package net.ktds.drink.admin.web.img;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.gameImages.biz.ImageBiz;
import net.ktds.drink.gameImages.biz.ImageBizImpl;
import net.ktds.drink.gameImages.vo.ImageVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.MultipartHttpServletRequest.MultipartFile;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;


public class DoImageUpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ImageBiz biz;
    public DoImageUpLoadServlet() {
        super();
        biz = new ImageBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartHttpServletRequest multipartRequest = 
				new MultipartHttpServletRequest(request);
		
		String imageTitle = multipartRequest.getParameter("imageTitle");
		String description = multipartRequest.getParameter("description");
		
		String fileName ="";		
		//jsp에서 받은 파라미터는 제목, 내용, 파일이름.
		
		MultipartFile uploadFile = multipartRequest.getFile("imgFile");
		
		//사용자가 파일을 업로드 했다면
		if(uploadFile.getFileSize() > 0) {
			//폴더 만들기
			File uploadedFileDirectory = new File("D:\\admin\\gameImages\\");
			uploadedFileDirectory.mkdirs();
			if(!uploadedFileDirectory.exists()) {
				uploadedFileDirectory.mkdirs();
			}
			
			uploadFile.write("D:\\admin\\gameImages\\" + uploadFile.getFileName());
			fileName = uploadFile.getFileName();
			System.out.println(fileName);
			
		}
		
		if(imageTitle.length() == 0) {
			response.sendRedirect("/Marble/admin/imageSet?errorCode=2");
			return;
		}
		if(description.length() == 0) {
			response.sendRedirect("/Marble/admin/imageSet?errorCode=2");
		}
		
		description = description.replaceAll("\n","<br/>")
									   .replaceAll("\r","");
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute(Session.USER_INFO);
		
		ImageVO imageFile = new ImageVO();
		imageFile.setManagerId("UR-20161018-000051");
		imageFile.setImageTitle(imageTitle);
		imageFile.setImageDescription(description);
		imageFile.setImageName(fileName);
		
		boolean isSuccess = biz.upLoadImage(imageFile);
		if(isSuccess) {
			response.sendRedirect("/Marble/admin/imageSet");
		}
		else {
			response.sendRedirect("/Marble/admin/imageSet?errorCode=1");
		}
	
	}

}
