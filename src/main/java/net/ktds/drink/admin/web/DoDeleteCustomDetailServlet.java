package net.ktds.drink.admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.support.Param;


public class DoDeleteCustomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;    

    public DoDeleteCustomDetailServlet() {
        super();
        biz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameId = Param.getStringParam(request, "gameId");
		
		
		if ( gameId.length() == 0) {
			response.sendRedirect("/Marble/admin/doDeleteCustomDetail?errorCode=1");
			return;
		}

		boolean isSuccess = biz.deleteCustom(gameId);
		biz.deleteGames(gameId);
		
		if ( isSuccess ) {
			response.sendRedirect("/Marble/admin/customList");
		}
		else {
			response.sendRedirect("/Marble/admin/doDeleteCustomDetail?errorCode=2");
		}
	}

}
