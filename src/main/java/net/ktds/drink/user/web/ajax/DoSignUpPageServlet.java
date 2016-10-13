package net.ktds.drink.user.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverXPointer;

import net.ktds.drink.support.Param;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;
import net.ktds.drink.user.vo.UserVO;

public class DoSignUpPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;
       
    public DoSignUpPageServlet() {
        super();
        userBiz = new UserBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String userEmail = Param.getStringParam(request, "userEmail");
		String userNickname = Param.getStringParam(request, "userNickname");
		String userPassword1 = Param.getStringParam(request, "userPassword1");
		String userPassword2 = Param.getStringParam(request, "userPassword2");
		
		
		String errorMessage = null;
		
		if( userEmail.length() == 0){
			errorMessage = "이메일을 입력하지 않았습니다.";
		}
		else if(userNickname.length() == 0){
			errorMessage = "닉네임을 입력하지 않았습니다.";
		}
		else if(userPassword1.length() == 0){
			errorMessage = "패스워드를 입력하지 않았습니다.";
		}
		else if(userPassword2.length() == 0){
			errorMessage = "패스워드를 입력하지 않았습니다.";
		}
		else if(userBiz.isExsistUserEmail(userEmail)){
			errorMessage = "이메일이 중복됩니다.";
		}
		else if(userBiz.isExsistUserNickname(userNickname)){
			errorMessage = "닉네임이 중복됩니다.";
		}
		else if( !userPassword1.equals(userPassword2)) {
			errorMessage = "비밀번호가 다릅니다.";
		}
		else{
			
			UserVO user = new UserVO();
			user.setUserEmail(userEmail);
			user.setUserNickname(userNickname);
			user.setUserPassword(userPassword1);
			userBiz.signUpUser(user);
			errorMessage = "success";
		}
	
		PrintWriter out = response.getWriter();
		out.write(errorMessage+"");
		out.flush();
		out.close();
	}

}
