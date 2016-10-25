package net.ktds.drink.admin.web.advertisement;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.admin.vo.AdvertisementVO;

public class ViewAdvertisementPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

	public ViewAdvertisementPageServlet() {
		super();
		adminBiz = new AdminBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String viewPath = "/WEB-INF/view/administer/advertisement.jsp";
		List<AdvertisementVO> advertisements = adminBiz.getAdvertisementVideo();
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		request.setAttribute("advertisements", advertisements);
		rd.forward(request, response);
	}

}
