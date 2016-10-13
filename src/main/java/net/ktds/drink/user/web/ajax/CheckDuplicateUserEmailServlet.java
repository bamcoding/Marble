package net.ktds.drink.user.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.support.Param;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;

public class CheckDuplicateUserEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;

	public CheckDuplicateUserEmailServlet() {
		super();
		userBiz = new UserBizImpl();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userEmail = Param.getStringParam(request, "userEmail");
		
		boolean isExsistUserEmail = userBiz.isExsistUserEmail(userEmail);
		
		
		PrintWriter out = response.getWriter();
		out.write(isExsistUserEmail + "");
		out.flush();
		out.close();
		
		
	}

}
