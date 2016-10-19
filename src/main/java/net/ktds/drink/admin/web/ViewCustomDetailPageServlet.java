package net.ktds.drink.admin.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class ViewCustomDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;      

    public ViewCustomDetailPageServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/administer/customDetail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		String gameId = Param.getStringParam(request, "gameId");

		CustomVO customVO = biz.getCustomDetailBy(gameId);
	
		request.setAttribute("customVO", customVO);
		rd.forward(request, response);
	}

}
