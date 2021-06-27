package book.Listener;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import book.dao.BookDao;

@WebListener
public class BookListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext(); //서블릿이 시작되면서 시작되는 보관소

			InitialContext initialContext = new InitialContext(); //톰캣 서버에서 자원찾기, web.xml에 자원을 참조한다고 선언필수!
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/book"); // java:comp/env/jdbc/ > 서버자원의 이름호출
			
			BookDao bookDao = new BookDao();
			bookDao.setDataSource(ds); //Dao객체에 주입 Datasource 주입
			
			sc.setAttribute("bookDao", bookDao); //servletcontext에 보관
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
	

}
