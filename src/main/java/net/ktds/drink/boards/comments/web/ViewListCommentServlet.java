package net.ktds.drink.boards.comments.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.comments.biz.CommentBiz;
import net.ktds.drink.boards.comments.biz.CommentBizImpl;
import net.ktds.drink.boards.comments.vo.CommentVO;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.support.Param;

public class ViewListCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentBiz commentBiz;
	
    public ViewListCommentServlet() {
        super();
        commentBiz = new CommentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String boardId = Param.getStringParam(request, "boardId");
		List<CommentVO> comments = commentBiz.getCommentsOf(boardId);
		
		String viewPath = "/WEB-INF/view/board/comment.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		request.setAttribute("comments", comments);
		rd.forward(request, response);
		
	}

}
