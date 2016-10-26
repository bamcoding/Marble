package net.ktds.drink.games.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.GameTypeBiz;
import net.ktds.drink.admin.biz.GameTypeBizImpl;
import net.ktds.drink.support.Param;


public class DoDeleteTypeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GameTypeBiz biz;

    public DoDeleteTypeDetailServlet() {
        super();
        biz = new GameTypeBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String typeId = Param.getStringParam(request, "typeId");
		
		boolean isSuccess = biz.deleteType(typeId);
		if ( isSuccess ) {
			response.sendRedirect("/Marble/admin/typeList");
		}
		else {
			response.sendRedirect("/Marble/admin/deleteTypeDetail?errorCode=2");
		}

		
		
	}

}
