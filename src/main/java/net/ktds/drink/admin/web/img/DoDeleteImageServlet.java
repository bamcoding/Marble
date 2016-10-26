package net.ktds.drink.admin.web.img;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.gameImages.biz.ImageBiz;
import net.ktds.drink.gameImages.biz.ImageBizImpl;
import net.ktds.drink.support.Param;

public class DoDeleteImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ImageBiz biz;
	Param param = new Param();
    public DoDeleteImageServlet() {
        super();
        biz = new ImageBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] imageId = request.getParameterValues("check");
		PrintWriter out = response.getWriter();	
		if(imageId == null || imageId.length == 0 || imageId.equals("")){
			out.write(false+"");
			out.flush();
			out.close();
		}
		else{	
			for ( int i = 0 ;  i< imageId.length ;i++ ){
				biz.deleteImage(imageId[i]);
			}
			out.write(true+"");
			out.flush();
			out.close();
		}
	}
}
