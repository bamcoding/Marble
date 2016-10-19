package net.ktds.drink.admin.web.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.admin.vo.UserListVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.support.pager.ClassicPageExplorer;
import net.ktds.drink.support.pager.PageExplorer;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;



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
		
		HttpSession session = request.getSession();
		int pageNo = Param.getIntParam(request, "pageNo", -1);
		SearchUserVO searchUser = null;
		int searchType = Param.getIntParam(request, "searchType");
		System.out.println("하나"+searchType);
		String searchKeyword = Param.getStringParam(request, "searchKeyword");
		
		if ( pageNo == -1 ){
			searchUser = (SearchUserVO)session.getAttribute(Session.SEARCH_USER_INFO);
			if ( searchUser == null){
				searchUser = new SearchUserVO();
				searchUser.setPageNumber(0);
			}
		}
		else {
			
			searchUser = new SearchUserVO();
			searchUser.setPageNumber(pageNo);
			searchUser.setSearchType(searchType);
			searchUser.setSearchKeyword(searchKeyword);
			
			
		}
		
		session.setAttribute(Session.SEARCH_USER_INFO, searchUser);
		UserListVO users = userBiz.getListUserInfo(searchUser);
		
		String viewPath = "/WEB-INF/view/administer/userInfo.jsp";
		
		request.setAttribute("users", users.getUsers());
		request.setAttribute("pager",users.getPager());
		
		PageExplorer pageExplorer = new ClassicPageExplorer(users.getPager());
		
		String pager = pageExplorer.getPagingList("pageNo", "[@]", "이전", "다음", "registForm");
		request.setAttribute("paging", pager);
		request.setAttribute("searchUser",searchUser);
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		rd.forward(request, response);

	}

}
