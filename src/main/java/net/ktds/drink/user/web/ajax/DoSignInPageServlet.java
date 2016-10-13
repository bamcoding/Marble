package net.ktds.drink.user.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;
import net.ktds.drink.user.vo.UserVO;

public class DoSignInPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;

	public DoSignInPageServlet() {
		super();
		userBiz = new UserBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String userEmail = Param.getStringParam(request, "userEmail");
		String userPassword = Param.getStringParam(request, "userPassword");
		PrintWriter out = response.getWriter();
		String message = null;
		boolean isSucessSignIn = false;
		if( userEmail.length() == 0 && userPassword.length() == 0) {
			message = "필수값을 입력하지 않았습니다.";
			out.write(message+"");
		}
		else{
			UserVO user = new UserVO();
			user.setUserEmail(userEmail);
			user.setUserPassword(userPassword);
			
			isSucessSignIn = userBiz.signIn(user,request);
			if ( isSucessSignIn ){
				message = "true";
				out.write(message+"");
			}
			else{
				message = "false";
				out.write(message+"");
			}
		}
		
		out.flush();
		out.close();
		
	
	}

}
