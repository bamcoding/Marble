package net.ktds.drink.admin.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class ViewGameDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;   

    public ViewGameDetailPageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/administer/gameDetail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		String gameId = Param.getStringParam(request, "gameId");

		GamesVO gameVO = biz.getGameDetailBy(gameId);
	
		
		request.setAttribute("gameVO", gameVO);
		rd.forward(request, response);
	}

}
