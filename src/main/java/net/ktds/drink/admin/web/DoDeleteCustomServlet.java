package net.ktds.drink.admin.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;


public class DoDeleteCustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;
    public DoDeleteCustomServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkbox = request.getParameterValues("checkbox");
		
		PrintWriter out = response.getWriter();
		
		if(checkbox==null || checkbox.length == 0) {
			out.print("다시선택하세요. ");
			return;
		}
		else {
			for(int i=0; i < checkbox.length; i++){
				biz.deleteCustom(checkbox[i]);
				biz.deleteGames(checkbox[i]);
			}
			out.print("삭제했습니다. ");
		}
		
		
		out.flush();
		out.close();
	}

}
