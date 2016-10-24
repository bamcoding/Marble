package net.ktds.drink.boards.comments.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.boards.comments.biz.CommentBiz;
import net.ktds.drink.boards.comments.biz.CommentBizImpl;
import net.ktds.drink.boards.comments.vo.CommentVO;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.MultipartHttpServletRequest;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;

public class DoWriteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentBiz commentBiz;
	
    public DoWriteCommentServlet() {
        super();
        commentBiz = new CommentBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String commentContent = Param.getStringParam(request, "commentContent");
		String boardId = Param.getStringParam(request, "boardId");
		
		commentContent = commentContent.replaceAll("\n", "<br/>").replaceAll("\r", "");
		
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute(Session.USER_INFO);
		
		CommentVO comment = new CommentVO();
		comment.setCommentId(commentBiz.getNewCommentId());
		comment.setBoardId(boardId);
		comment.setCommentContent(commentContent);
		comment.setUserId(userVO.getUserId());
		comment.setCreatedDate(commentBiz.getTime());
		comment.setModifyDate(commentBiz.getTime());
		
		comment.getUserVO().setUserNickname(userVO.getUserNickname());
		
		commentBiz.addComment(comment);
		
		PrintWriter out = response.getWriter();
		out.append(comment.toString());
		out.flush();
		out.close();
	}

}
