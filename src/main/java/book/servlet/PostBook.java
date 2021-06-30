package book.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookDao;
import book.vo.Book;

@WebServlet("/book/PostBook")
public class PostBook extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		String type = request.getParameter("type");
		
		if(type.equals("list") || type==null) {
			try {		    
				ServletContext sc = this.getServletContext();
				BookDao bookDao = (BookDao)sc.getAttribute("bookDao");
				request.setAttribute("bookDao", bookDao.booklist());
				
				//JSP로 출력을 위임
				response.setContentType("text/html; charset=UTF-8");
				request.setAttribute("type", type);
				RequestDispatcher rd = request.getRequestDispatcher("/book/PostBook.jsp");
				rd.forward(request, response);
			}catch(Exception e) {
				//예외발생 시 ServletException 객체에 담아 서블릿 컨테이너로 이동, 서블릿 컨테이너가 클라이언트에게 적절한 화면 노출
				throw new ServletException(e);
			}
		}
		else {
			//신규등록 및 수정업데이트
			request.setAttribute("type", type);
			RequestDispatcher rd = request.getRequestDispatcher("/book/PostBookUpdate.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
}
