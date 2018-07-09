package com.example.test.configration.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.samskivert.mustache.Mustache;

@Component
public class CsrfInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(
		HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) throws Exception {
		if (mav != null) {
			// _csrf 를 찾아.
			mav.addObject("_csrf", (Mustache.Lambda) (frag, out) -> {
				// token 에 해당할 경우
				if ("token".equals(frag.execute())) {
					// 토큰으로 치환
					CsrfToken csrfToken = ((CsrfToken) req.getAttribute(CsrfToken.class.getName()));
					if (null != csrfToken) {
						out.write(csrfToken.getToken());
					}
				}
			});
		}

		super.postHandle(req, res, handler, mav);
	}
}