package com.zhiyou100.video.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion放行");

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle放行");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		String pwd = req.getParameter("loginPwd");
		HttpSession session = req.getSession();
		System.out.println(pwd);
		if(pwd!=null){
			return true;
		}
		else if (session.getAttribute("admin") != null) {
	    	return true;
	    }
		else {
	    	req.setAttribute("errorMessage", "请登录");
	    	req.getRequestDispatcher("/admin/index.jsp").forward(req, res);
	        return false;
	    }
	}

}
