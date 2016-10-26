package net.ktds.drink.boards.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.support.Param;

public class ViewWritePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardBiz boardBiz;
	
    public ViewWritePageServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = Param.getStringParam(request, "categoryId");
		CategoryVO category = boardBiz.getCategoryName(categoryId);
		
		String viewPath = "/WEB-INF/view/board/write.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		request.setAttribute("category", category);
		rd.forward(request, response);
	}

}
