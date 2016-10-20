package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.boards.biz.BoardBiz;
import net.ktds.drink.boards.biz.BoardBizImpl;

public class DoArticleInfoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardBiz boardBiz;
       
    public DoArticleInfoDeleteServlet() {
        super();
        boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] checks = request.getParameterValues("checks");
		PrintWriter out = response.getWriter();
		if( checks == null || checks.length == 0 || checks.equals("")){
			out.write(false+"");
			out.flush();
			out.close();
		}
		else {
			for( int i=0; i<checks.length; i++ ){
				boardBiz.removeBoard(checks[i]);
			}
			
			out.write(true+"");
			out.flush();
			out.close();
		}
	}

}
