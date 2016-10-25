package net.ktds.drink.admin.web.advertisement;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;

import net.ktds.drink.support.Param;
import net.ktds.drink.support.VideoStreamingUtil;

public class DoShowAdvertisementVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

	public DoShowAdvertisementVideoServlet() {
		super();
		adminBiz = new AdminBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		String advertisementId = Param.getStringParam(request, "advertisementId");
		String fileName = adminBiz.getFileNameofAdvertisementBy(advertisementId);
		
        if( fileName != null && fileName.length() > 0 ){
        	
        	VideoStreamingUtil downloadUtil = VideoStreamingUtil.getInstance("D:\\marble\\uploadfiles");
            
            downloadUtil.download(request, response, fileName, fileName);
        }
		
	}

}
