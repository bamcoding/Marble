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
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.biz.UserBiz;
import net.ktds.drink.user.biz.UserBizImpl;
import net.ktds.drink.user.vo.UserVO;

/**
 * 세션필터, 포인트 업데이트(글 읽었을때 포인트?)
 * @author Eun-joo
 *
 */
public class ViewDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardBiz boardBiz;
	//private UserBiz userBiz;
	
    public ViewDetailPageServlet() {
        super();
        boardBiz = new BoardBizImpl();
        //userBiz = new UserBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String boardId = Param.getStringParam(request, "boardId");
		
		boardBiz.updateHitCount(boardId);
		BoardVO board = boardBiz.getBoardAt(boardId);
		
		//HttpSession session = request.getSession();
		//UserVO userVO = (UserVO)session.getAttribute(Session.USER_INFO);
		//String userId = userVO.getUserId();
		//
		
		String viewPath = "/WEB-INF/view/board/detail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		request.setAttribute("board", board);
		rd.forward(request, response);
	}

}
