package web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EncodingFilter
 */
public class EncodingFilter extends HttpServlet implements Filter{
	private String encoding;
	public EncodingFilter(){
		encoding = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//获得在web.xml中设置的编码
		encoding = filterConfig.getInitParameter("encoding");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(encoding != null){
			//在每次请求中都将数据过滤
			request.setCharacterEncoding(encoding);
			chain.doFilter(request, response);
		}
		
	}
	
}
