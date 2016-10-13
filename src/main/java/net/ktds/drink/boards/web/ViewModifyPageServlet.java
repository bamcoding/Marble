package net.ktds.drink.boards.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.support.Param;

public class ViewModifyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardBiz boardBiz;
       
    public ViewModifyPageServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String boardId = Param.getStringParam(request, "boardId");
		BoardVO board = boardBiz.getBoardForModify(boardId);
		
		String content = board.getBoardContent();
		content = content.replaceAll("<br/>", "\n");
		content = content.trim();
		content.trim();
		board.setBoardContent(content);
		
		String viewPath = "/WEB-INF/view/board/modify.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		request.setAttribute("board", board);
		
		rd.forward(request, response);
		
	}

}
