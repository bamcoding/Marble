package net.ktds.drink.games.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;


public class IsExistGameName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private GamesBiz biz;   

    public IsExistGameName() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameName = request.getParameter("gameName");
		
		boolean isExsistGameName = biz.isExsistGameName(gameName);
		
		PrintWriter out = response.getWriter();
		out.write("<div id='bo'><input type='text' value='" + isExsistGameName + "'/></div>");
		out.flush(); //보내줭
		out.close();
	}

}
