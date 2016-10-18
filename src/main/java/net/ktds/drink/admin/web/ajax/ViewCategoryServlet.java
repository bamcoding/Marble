package net.ktds.drink.admin.web.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.category.biz.CategoryBiz;
import net.ktds.drink.category.biz.CategoryBizImpl;
import net.ktds.drink.category.vo.CategoryVO;

public class ViewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<CategoryVO> categories;
    private CategoryBiz biz = new CategoryBizImpl();
    public ViewCategoryServlet() {
    	super();
    	categories = new ArrayList<CategoryVO>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		categories = biz.getAllCategory("0"); 

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administer/categoryInfo.jsp");
		List<CategoryVO> tempList = null;
		for(CategoryVO categoryVO : categories){
			String temp = null;
			if(categoryVO.getCategoryId() != temp){
				tempList = new ArrayList<CategoryVO>();
			}
			request.setAttribute(categoryVO.getCategoryId(), categories);
		}
		
		request.setAttribute("categories", categories);
		rd.forward(request, response);
	}

}
