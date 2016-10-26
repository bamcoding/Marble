package net.ktds.drink.admin.web.img;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.gameImages.biz.ImageBiz;
import net.ktds.drink.gameImages.biz.ImageBizImpl;
import net.ktds.drink.gameImages.vo.ImageVO;
import net.ktds.drink.support.Param;

public class ViewImageSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ImageBiz biz;
    Param param = new Param();
    public ViewImageSetServlet() {
        super();
        biz = new ImageBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorCode = param.getStringParam(request, "errorCode");
		if(errorCode.length()==0){
			errorCode="";
		}
		
		List<ImageVO> images = biz.getAllImageList(); 
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/image/imageSet.jsp");
		request.setAttribute("images", images);
		rd.forward(request, response);
	}

}
