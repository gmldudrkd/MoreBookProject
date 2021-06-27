package book.servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import book.dao.BookDao;


@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
		rd.forward(request, response); //다시 돌아올 필요없음 > 재요청할거임
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");    
		try {		    
			ServletContext sc = this.getServletContext();
			BookDao bookDao = (BookDao)sc.getAttribute("bookDao");
			
			request.setAttribute("bookDao", bookDao.exist(request.getParameter("userid"), request.getParameter("password")));
			
			if(request.getAttribute("bookDao") != null) {
				HttpSession session = request.getSession(); //로그인 ~ 로그아웃 까지 보관되는 데이터 저장소
		        session.setAttribute("book", request.getAttribute("bookDao"));
				response.sendRedirect("../book/main");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}

}
