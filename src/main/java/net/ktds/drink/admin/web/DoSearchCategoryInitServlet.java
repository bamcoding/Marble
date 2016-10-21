package net.ktds.drink.admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;


public class DoSearchCategoryInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DoSearchCategoryInitServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId= Param.getStringParam(request, "categoryId");
		HttpSession session = request.getSession();
		session.removeAttribute(Session.SEARCH_GAME_CATEGORY_INFO);
	
		response.sendRedirect("/Marble/admin/gameMenuList?categoryId="+categoryId);
	}

}
