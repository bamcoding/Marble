package net.ktds.drink.admin.web.gameType;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.admin.biz.GameTypeBiz;
import net.ktds.drink.admin.biz.GameTypeBizImpl;

public class DoDeleteTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GameTypeBiz biz;  

    public DoDeleteTypeServlet() {
        super();
        biz = new GameTypeBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkbox = request.getParameterValues("checkbox");
		
		PrintWriter out = response.getWriter();
		
		if(checkbox == null || checkbox.length == 0) {
			out.print("다시선택하세요. ");
			return;
		}
		else {
			for(int i=0; i < checkbox.length; i++){
				biz.deleteType(checkbox[i]);
			}
			out.print("삭제했습니다. ");
		}
		out.flush();
		out.close();
	}

}
