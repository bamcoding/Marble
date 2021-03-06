package net.ktds.drink.boards.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.constants.Admin;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;

public class GoToListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardBiz boardBiz;
    public GoToListServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String categoryId = Param.getStringParam(request, "categoryId");
		BoardVO board = new BoardVO();
		board.setCategoryId(categoryId);
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute(Session.USER_INFO);
		String userEmail = user.getUserEmail();
		
		
		if(userEmail.equals(Admin.ADMIN)){
				response.sendRedirect("/Marble/admin/articleInfo?categoryId=" + categoryId);
			}else{
				response.sendRedirect("/Marble/board/list?categoryId=" + categoryId);
			}
		}
	
	}

	
	