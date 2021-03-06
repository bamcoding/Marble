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

import net.ktds.drink.constants.Session;
import net.ktds.drink.user.vo.UserVO;

public class SessionCheckFilter implements Filter {

	public SessionCheckFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = ((HttpServletRequest) request).getSession();
		PrintWriter out = (response).getWriter();
		
		if (  session.getAttribute(Session.USER_INFO) == null){
			
			out.write("<script type='text/javascript'> ");
			out.write(" location.href='/Marble/play/index'; ");
			out.write(" alert('로그인이 필요합니다.'); ");
			out.write("</script>");
			out.flush();
			out.close();
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
