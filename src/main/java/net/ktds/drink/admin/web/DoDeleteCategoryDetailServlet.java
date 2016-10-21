package net.ktds.drink.admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.support.Param;


public class DoDeleteCategoryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;   
 
    public DoDeleteCategoryDetailServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameId = Param.getStringParam(request, "gameId");
		String categoryId = Param.getStringParam(request, "categoryId");
		
		
		if ( gameId.length() == 0) {
			response.sendRedirect("/Marble/admin/doDeleteDetail?errorCode=1");
			return;
		}
		
		boolean isSuccess = biz.deleteGames(gameId);
		if ( isSuccess ) {
			response.sendRedirect("/Marble/admin/gameMenuList?categoryId="+categoryId);
		}
		else {
			response.sendRedirect("/Marble/admin/doDeleteDetail?errorCode=2");
		}
		
	}

}
