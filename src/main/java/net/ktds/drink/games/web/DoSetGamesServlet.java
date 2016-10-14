package net.ktds.drink.games.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.support.Param;


public class DoSetGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public DoSetGamesServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String games = Param.getStringParam(request, "games");
		String numbers = Param.getStringParam(request, "numbers");
		
		System.out.println("num" + numbers);
	}

}
