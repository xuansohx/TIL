package com.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({ "/RequestServlet", "/req" })
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 데이터 받기 위하여 request에서 설정 (안 그러면 깨짐)
		String type = request.getParameter("type"); // user를 원하는지, product를 원하는지 꺼내줌
		if(type == null || type.equals("")) {
			type = "index.jsp";
		}
		RequestDispatcher rd = 
		request.getRequestDispatcher(type);
		rd.forward(request, response);
	}

}






