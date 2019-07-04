package com.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.Biz;
import com.user.UserBiz;
import com.vo.User;

@WebServlet({ "/UserServlet", "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Biz<String, User> biz;

	public UserServlet() {
		biz = new UserBiz();
	} // 여기는 요청을 받고 메모리에 올라갈 때 한 번 호출되고
		// 그 이후에는 service만 호출

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String next = "";
		if (cmd.equals("add")) {
			next = "user/add";
		} else if (cmd.equals("addimpl")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");

			try { // 정상
				biz.register(new User(id, name, pwd));
				request.setAttribute("rid", id);
				next = "user/rok"; // 페이지 이동
			} catch (Exception e) { // 비정상
				next = "user/rfail";
			}

			// System.out.println(id+" "+name); → 이거는 현업에서 주석으로도 사용하지 않기!!!

		} else if (cmd.equals("list")) {
			ArrayList<User> list = null;
			try {
				list = biz.get();
				request.setAttribute("ulist", list);
			} catch (Exception e) {

			}
			next = "user/list";
		} else if (cmd.equals("detail")) {
			String id = request.getParameter("id");
			User user = null;
			try {
				user = biz.get(id);
				request.setAttribute("ud", user); // user 정보 담아서
				next = "user/detail"; // 페이지 이동
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("delete")) {
			String id = request.getParameter("id");
			try {
				biz.remove(id);
				response.sendRedirect("req?type=user&cmd=list"); // remove한 후 다시 list page
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("update")) {
			String id = request.getParameter("id");
			User user = null;
			try {
				user = biz.get(id);
				request.setAttribute("uu", user); // user 정보 담아서
				next = "user/update"; // 페이지 이동
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("updateimpl")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");

			try { // 정상
				biz.modify(new User(id, name, pwd));
				response.sendRedirect("req?type=user&cmd=detail&id=" + id);
				return;
			} catch (Exception e) { // 비정상
				e.printStackTrace();
				next = "user/rfail";
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(next + ".jsp");
		rd.forward(request, response);
	}

}
