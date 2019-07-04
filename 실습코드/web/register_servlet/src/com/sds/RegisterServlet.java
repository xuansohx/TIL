package com.sds;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/RegisterServlet", "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Register Completed..");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String usertype = request.getParameter("type");
		String academic = request.getParameter("academic");
		String language[] = request.getParameterValues("language");
		

		System.out.println(
				name + " " + id + " " + pwd + " " + usertype + " " +academic+" "+Arrays.toString(language));

		// HTML5 : 서버프로그램의 결과를 책임
		request.setAttribute("name", name);
		request.setAttribute("type", usertype);
		request.setAttribute("academic", academic);
		request.setAttribute("language", language);
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response); // request에 보내고자 하는 데이터 담은 후, forward로 보냄

	}

}
