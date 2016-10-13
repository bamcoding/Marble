package net.ktds.bamcoding.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 이근재가 만든 jsp파일을 브라우저에 뿌려주기 위해 만든 서블릿이다.
 * 경로목록
 * /WEB-INF/view/bamcoding/test.jsp
 * /WEB-INF/view/bamcoding/resources/carousel.jsp
 * /WEB-INF/view/bamcoding/resources/cube.jsp
 * /WEB-INF/view/bamcoding/resources/flip.jsp
 * /WEB-INF/view/bamcoding/resources/move.jsp
 * /WEB-INF/view/bamcoding/resources/throw.jsp
 * @author 206-017
 *
 */
public class ViewTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewTestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/bamcoding/carousel.jsp");
		rd.forward(request, response);
	
	}
	

}
