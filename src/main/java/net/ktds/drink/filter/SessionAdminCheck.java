package net.ktds.drink.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Admin;
import net.ktds.drink.constants.Session;
import net.ktds.drink.user.vo.UserVO;

public class SessionAdminCheck implements Filter {
	public SessionAdminCheck() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
/*		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = ((HttpServletRequest) request).getSession();
		PrintWriter out = (response).getWriter();
		UserVO admin = (UserVO)session.getAttribute(Session.USER_INFO);
		
		if ( admin == null || !admin.getUserEmail().equals(Admin.ADMIN) ){
			//어드민이 아니면 접근불가하고 
			out.write("<script type='text/javascript'> ");
			out.write(" location.href='/Marble/play/index'; ");
			out.write(" alert('접근 권한이 없습니다.'); ");
			out.write("</script>");
			out.flush();
			out.close();
			return;
		}*/
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
