package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.support.Param;

public class CheckExistImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GamesBiz gamesBiz;
	
    public CheckExistImageServlet() {
        super();
        gamesBiz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String gameId = Param.getStringParam(request, "gameId");
		String type = Param.getStringParam(request, "type");
		
		String fileName = null;
		
		if(type.equals("detail") ){
			fileName = gamesBiz.getDetailImageofGamesBy(gameId);
		}else if (type.equals("cell") ){
			fileName = gamesBiz.getCellImageofGamesBy(gameId);
		}
		
		boolean isExist = false;
		if(fileName != null){
			isExist = true;
		}else{
			isExist = false;
		}
		
		PrintWriter out = response.getWriter();
		out.append(isExist + "");
		out.flush();
		out.close();
		
		
		
	}

}
