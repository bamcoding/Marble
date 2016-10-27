package net.ktds.drink.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.support.Param;

public class ViewAddCategoryGamePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;

    public ViewAddCategoryGamePageServlet() {
        super();
        biz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/view/administer/addCategoryGame.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		CategoryVO categoryVO = new CategoryVO();
		//부모 카테고리 = 게임 
		categoryVO.setParentCategoryId("10");
		List<CategoryVO> categories = biz.getAdminCategory(categoryVO);
		
		String categoryId = Param.getStringParam(request, "categoryId");
		String categoryName = Param.getStringParam(request, "categoryName");
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("categories", categories);

		rd.forward(request, response);
	}

}
