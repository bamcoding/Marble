package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.user.dao.UserDao;
import net.ktds.drink.user.dao.UserDaoImpl;

public class DoUserInfoDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userBiz;

	public DoUserInfoDelete() {
		super();
		userBiz= new UserDaoImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String[] checks = request.getParameterValues("checks");
		PrintWriter out = response.getWriter();	
		if(checks == null || checks.length == 0 || checks.equals("")){
			out.write(false+"");
			out.flush();
			out.close();
		}
		else{	
				
			for ( int i = 0 ;  i< checks.length ;i++ ){
				userBiz.deleteUserInfo(checks[i]);
			}
			System.out.println("된당");
			out.write(true+"");
			out.flush();
			out.close();
		}
		
		
		
	}

}
