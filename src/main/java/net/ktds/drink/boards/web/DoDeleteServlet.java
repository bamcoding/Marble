package net.ktds.drink.boards.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.Param;

public class DoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		private BoardBiz boardBiz;
       
    public DoDeleteServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String boardId = Param.getStringParam(request, "boardId");
		String categoryId = Param.getStringParam(request, "categoryId");
		
		BoardVO board = new BoardVO();
		board.setCategoryId(categoryId);
		
		boolean isSuccess = boardBiz.removeBoard(boardId);
		if ( isSuccess ){
			response.sendRedirect("/Marble/board/list?categoryId=" +categoryId);
		}
		else {
			response.sendRedirect("/Marble/board/detail?boardId=" + boardId);
		}
	}
}
