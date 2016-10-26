package net.ktds.drink.games.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;


public class ViewSetGamesPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GamesBiz biz;
	

    public ViewSetGamesPageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute(Session.USER_INFO);
		
		String userId;
		if(user != null){
			userId = user.getUserId();
		}else{
			userId = "anonymous";
		}
		
		String viewPath = "/WEB-INF/view/game/setGames.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setParentCategoryId("10");
		List<CategoryVO> categories = biz.getCategory(categoryVO);
		
		
		List<GamesVO> games = biz.allGetGames(userId);
		
		request.setAttribute("games", games);
		request.setAttribute("categories", categories);

		   
		rd.forward(request, response);
		
	
		
	}

}
