package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;
import net.ktds.drink.user.vo.UserVO;



public class ViewUserInfoListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz;

	public ViewUserInfoListPageServlet() {
		super();
		userBiz = new UserBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SearchUserVO searchUser = null; 
		List<UserVO> users = userBiz.getListUserInfo(searchUser);  
		String viewPath = "/WEB-INF/view/administer/userInfo.jsp";
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);

	}

}
