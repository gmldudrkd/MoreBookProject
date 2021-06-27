package book.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.dao.BookDao;
import book.vo.Book;

@WebServlet("/auth/signup")
public class SignupServlet extends  HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");    
		
		String mode = request.getParameter("mode");
		if(mode.equals("loginform")) {
			RequestDispatcher rd = request.getRequestDispatcher("/auth/SignupForm.jsp");
			rd.forward(request, response); //다시 돌아올 필요없음 > 재요청할거임
		}else {
			insertinfo(request, response);
		}
	}
	
	protected void insertinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {		    
			ServletContext sc = this.getServletContext();
			BookDao bookDao = (BookDao)sc.getAttribute("bookDao");
			
			//등록하기
			request.setAttribute("bookDao", bookDao.insertauth( 
					new Book().setUserid(request.getParameter("userid"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name"))
					));
			
			response.sendRedirect("login");
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/book/Error.jsp");
			rd.forward(request, response);
		}
	}
	
}
