package net.ktds.drink.play.web.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;
import net.ktds.drink.admin.vo.AdvertisementVO;

public class ViewAdvertisementVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

	public ViewAdvertisementVideoServlet() {
		super();
		adminBiz = new AdminBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		adminBiz = new AdminBizImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdvertisementVO advertisement = adminBiz.getRandomAdvertisementVideoBy();
		String viewPath = "/WEB-INF/view/play/advertiseMent.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		System.out.println(advertisement.getAdvertisementId());
		request.setAttribute("advertisement", advertisement);
		
		rd.forward(request, response);
	}

}
