package ru.ovchinnikov.springuserservice.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Данный фильтр организует проверку приложений на доступ , если есть доступ то обрабатываеться запрос дальше,
 * иначе возврощаем с кодом 403 и прекращаем обработку
 */
public class SecurityFilter implements Filter {


	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
						 FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// Тут свой способ авторизации
		String auth = request.getHeader("Authorization");
		if (auth!=null && auth.equals("12345")) chain.doFilter(req, res);
		else response.setStatus(403);

	}

	@Override
	public void destroy() {
	}

}



