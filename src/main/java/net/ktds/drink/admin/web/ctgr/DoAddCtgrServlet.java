package net.ktds.drink.admin.web.ctgr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.category.biz.CategoryBiz;
import net.ktds.drink.category.biz.CategoryBizImpl;
import net.ktds.drink.support.Param;

public class DoAddCtgrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Param param = new Param();
    CategoryBiz biz;
    public DoAddCtgrServlet() {
        super();
        biz = new CategoryBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = param.getStringParam(request, "ctgr_input");
		String parentName = param.getStringParam(request, "selected_info");
		boolean isExist = biz.checkExistName(name);
		boolean isTrue = false;
		
		if(!isExist){
			isTrue = biz.addCategory(name, parentName);
		}
		System.out.println("추가 : "+isTrue);
		PrintWriter out = response.getWriter();
		out.write(isTrue+"");
		out.flush();
		out.close();
	}

}
