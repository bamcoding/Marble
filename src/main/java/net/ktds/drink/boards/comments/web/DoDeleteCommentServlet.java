package net.ktds.drink.boards.comments.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.comments.biz.CommentBiz;
import net.ktds.drink.boards.comments.biz.CommentBizImpl;
import net.ktds.drink.support.Param;

public class DoDeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentBiz commentBiz;
	
    public DoDeleteCommentServlet() {
        super();
        commentBiz = new CommentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = Param.getStringParam(request, "commentId");
		commentBiz.removeComment(commentId);
	}

}
