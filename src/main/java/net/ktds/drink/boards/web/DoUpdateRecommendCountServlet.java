package net.ktds.drink.boards.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.support.Param;

public class DoUpdateRecommendCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
		private BoardBiz boardBiz;
       
    public DoUpdateRecommendCountServlet() {
        super();
        this.boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardId = Param.getStringParam(request, "boardId");
		
		boolean isSuccess = boardBiz.updateRecommendCount(boardId);
		
		PrintWriter out = response.getWriter();
		out.write(isSuccess + "");
		out.flush();
		out.close();
	}

}
