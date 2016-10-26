package net.ktds.drink.admin.web.gameType;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Session;
import net.ktds.drink.support.Param;


public class DoSearchTypeInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DoSearchTypeInitServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(Session.SEARCH_TYPE_INFO);
	
		response.sendRedirect("/Marble/admin/typeList");
	}

}
