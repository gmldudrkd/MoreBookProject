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

@WebServlet("/book/PostBookUpdate")
public class PostBookUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String type = request.getParameter("type");
		request.setAttribute("type", type);

		if(type.equals("update")) {
			try {		    
				ServletContext sc = this.getServletContext();
				BookDao bookDao = (BookDao)sc.getAttribute("bookDao");
				request.setAttribute("bookDao", bookDao.bookselect(Integer.parseInt(request.getParameter("num"))));
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}else {
			request.setAttribute("bookDao", new Book());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/book/PostBookUpdate.jsp");
		rd.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type = request.getParameter("type");
		
		if(type.equals("new")) { //등록
			try {
				ServletContext sc = this.getServletContext();
				BookDao bookDao = (BookDao)sc.getAttribute("bookDao");
				request.setAttribute("bookDao", bookDao.insertPostbook(
						new Book().setPosttitle(request.getParameter("posttitle"))
						.setPostdesc( request.getParameter("postdesc"))
						.setEvaluate(request.getParameter("evalsel"))
						.setReadDatest(request.getParameter("startdate"))
						.setReadDateen(request.getParameter("enddate"))
						));				
				response.sendRedirect("PostBook?type=list");
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
		else {
			try {
				ServletContext sc = this.getServletContext();
				BookDao bookDao = (BookDao)sc.getAttribute("bookDao");
				request.setAttribute("BookDao", bookDao.updatePostbook(new Book().setPosttitle(request.getParameter("posttitle"))
						.setPostdesc( request.getParameter("postdesc"))
						.setReadDatest(request.getParameter("startdate"))
						.setNo(Integer.parseInt(request.getParameter("no")))
						.setReadDateen(request.getParameter("enddate")) ));
				
				response.sendRedirect("PostBook?type=list");
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}

}
