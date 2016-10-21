package net.ktds.drink.boards.comments.web;

import java.io.IOException;
import java.util.List; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.comments.biz.CommentBiz;
import net.ktds.drink.boards.comments.biz.CommentBizImpl;
import net.ktds.drink.boards.comments.vo.CommentVO;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.Param;

public class DomodifyCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentBiz commentBiz;
	
    public DomodifyCommentServlet() {
        super();
        commentBiz = new CommentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		
		String commentId = multipartRequest.getParameter("commentId");
		String commentContent = multipartRequest.getParameter("commentContent");
		
		commentContent = commentContent.replaceAll("\n", "<br/>") .replaceAll("\r", "");
	
		CommentVO comment = new CommentVO();
		comment.setCommentId(commentId);
		comment.setCommentContent(commentContent);
			
	}

}
