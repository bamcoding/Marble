package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.support.Param;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;

public class DoPasswordResetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;
	
	public DoPasswordResetServlet() {
		super();
		userBiz = new UserBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String[] checked = request.getParameterValues("checks");
		
		
		
		if ( checked == null || checked.length == 0 || checked.equals("") ){
			out.write( false + "");
			out.flush();
			out.close();
		}
		
		int isSuccess = 0;
		for(String userId : checked){
			userBiz.userPasswordReset(userId);
			isSuccess++;
		}
		
		if (isSuccess == 0){
			out.write(false + "");
			out.flush();
			out.close();
		}
		else{
			out.write(true + "");
			out.flush();
			out.close();
		}
		
		
		
		
		 
	
		
	}

}
