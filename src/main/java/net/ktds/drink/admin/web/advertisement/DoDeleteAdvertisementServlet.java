package net.ktds.drink.admin.web.advertisement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.AdminBiz;
import net.ktds.drink.admin.biz.AdminBizImpl;

public class DoDeleteAdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminBiz adminBiz;

	public DoDeleteAdvertisementServlet() {
		super();
		adminBiz = new AdminBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			String[] checks = request.getParameterValues("checks");
		PrintWriter out = response.getWriter();
		
		if(checks==null || checks.length == 0) {
			out.print("다시선택하세요. ");
			return;
		}
		else {
			for(int i=0; i < checks.length; i++){
				adminBiz.deleteAdvertisement(checks[i]);
			}
			out.print("삭제했습니다. ");
		}
		
		
		out.flush();
		out.close();
	}

}
