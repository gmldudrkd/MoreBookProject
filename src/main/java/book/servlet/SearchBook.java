package book.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import book.vo.Book;

@WebServlet("/book/SearchBook")
public class SearchBook extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("setcheck", "");
		RequestDispatcher rd = request.getRequestDispatcher("/book/SearchBook.jsp");
		rd.forward(request, response); //다시 돌아올 필요없음 > 재요청할거임
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String clientId = "4827ZyJ9iWaIVl2O5akZ";
		String clientSecret = "_ksDc5UO_2"; 
		String bookname = request.getParameter("searchfield");
				
		String apiURL = "https://openapi.naver.com/v1/search/book.xml?query=" + toUTF8(bookname); //책기본검색
		try {
			 //네이버 책검색 API사용
			  URL url = new URL(apiURL);
			  HttpURLConnection con = (HttpURLConnection)url.openConnection();
			  con.setRequestMethod("GET");
			  con.setRequestProperty("X-Naver-Client-Id", clientId);
			  con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			  int responseCode = con.getResponseCode();
			  BufferedReader br;
			  if(responseCode==200) { // 정상 호출
				  br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			  } else {  // 에러 발생
				  br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			  }
			  String inputLine;
			  StringBuffer res = new StringBuffer();
			  while ((inputLine = br.readLine()) != null) {
			    res.append(inputLine);
			  }
			  br.close();
			  
			  if(responseCode == 200) {
				  request.setAttribute("bookitem", getXmlData(res.toString()));
				  request.setAttribute("setcheck", "Y");				  
			  }else {
				  request.setAttribute("setcheck", "");
			  }
			  request.setAttribute("searchfield", bookname);
			  
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/book/SearchBook.jsp");
			rd.forward(request, response);
  
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	String title = null;
    String author = null;
    String price = null;
    String publisher = null;
    String pubdate = null;
    String isbn = null;
    String description = null;
    String image = null;
	
	//XML파서 이용
	public ArrayList<HashMap<String,String>> getXmlData(String string) throws Exception {	
		
		StringBuffer sb      =  new StringBuffer();
		sb.append(string);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();//빌더 팩토리 생성.
        DocumentBuilder builder = builderFactory.newDocumentBuilder(); //빌더 생성
        Document document     =  builder.parse(new InputSource(new StringReader(sb.toString()))); //스트링을 파싱해서 Document 객체로 가져온다.
        
        // XML 데이터 중 <item> 태그의 내용을 가져온다.
        NodeList bookTagList = document.getElementsByTagName("item");

        // <person> 태그 리스트를 하나씩 돌면서 값들을 가져온다.
        ArrayList<HashMap<String,String>> bookitems = new ArrayList<HashMap<String,String>>();
        for (int i = 0; i < bookTagList.getLength(); ++i) {            
            // <item> 태그의 하위 노드들을 가져온다. ( 여기서 노드는 태그를 의미한다. )
            NodeList childNodes = bookTagList.item(i).getChildNodes();
            HashMap<String,String> bookitems_set = new HashMap<>();
            for (int j = 0; j < childNodes.getLength(); ++j) {
                if ("title".equals(childNodes.item(j).getNodeName())) {
                	title = childNodes.item(j).getTextContent();
                	bookitems_set.put("title", title);
                }
                if ("author".equals(childNodes.item(j).getNodeName())) {
                	 author = childNodes.item(j).getTextContent();
                	 bookitems_set.put("author", author);
                }
                if ("price".equals(childNodes.item(j).getNodeName())) {
                	price  = childNodes.item(j).getTextContent();
                	bookitems_set.put("price", price);
                }
                if ("publisher".equals(childNodes.item(j).getNodeName())) {
                	publisher = childNodes.item(j).getTextContent();
                	bookitems_set.put("publisher", publisher);
                }
                if ("pubdate".equals(childNodes.item(j).getNodeName())) {
                	 pubdate  = childNodes.item(j).getTextContent();
                	 bookitems_set.put("pubdate", pubdate);
                }
                if ("isbn".equals(childNodes.item(j).getNodeName())) {
                	isbn = childNodes.item(j).getTextContent();
                	bookitems_set.put("isbn", isbn);
                }
                if ("image".equals(childNodes.item(j).getNodeName())) {
                	image = childNodes.item(j).getTextContent();
                	bookitems_set.put("image", image);
                }
                if ("description".equals(childNodes.item(j).getNodeName())) {
                	description = childNodes.item(j).getTextContent();
                	bookitems_set.put("description", description);
                }
            }
            
            bookitems.add(bookitems_set);
        }
        return bookitems;
	}
	
	//인코딩 함수
	private static String toUTF8(String str){
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str; // 변환 실패 시 기존str
        }
    }
	
}
