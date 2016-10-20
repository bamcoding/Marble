package net.ktds.drink.admin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CategoryVO;


public class ViewGameCategoryMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;
    
    public ViewGameCategoryMenuServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryVO categoryVO = new CategoryVO();
		//부모 카테고리 = 게임 
		categoryVO.setParentCategoryId("10");
		List<CategoryVO> categories = biz.getAdminCategory(categoryVO);

		
		StringBuffer options = new StringBuffer();
		for (CategoryVO category : categories) {
			options.append(String.format("<a href=\"/Marble/admin/gameMenuList?categoryId=%s\">%s</a>", category.getCategoryId(), category.getCategoryName() ));
		}
		
		PrintWriter out = response.getWriter();
		out.write(options.toString());
		out.flush();
		out.close();
	
	
	
	}

}
