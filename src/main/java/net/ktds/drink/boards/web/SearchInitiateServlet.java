package net.ktds.drink.boards.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;

public class SearchInitiateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;
       
    public SearchInitiateServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = Param.getStringParam(request, "categoryId");
		
		HttpSession session = request.getSession();
		session.removeAttribute(Session.SEARCH_INFO);
		request.setAttribute("categoryId", categoryId);
		
		response.sendRedirect("/Marble/board/list?categoryId=" + categoryId);
	}

}
