package co.nambaone.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nambaone.prj.common.Command;
import co.nambaone.prj.movie.command.MainCommand;

@WebServlet("*.do")
public class frontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// HashMap : 아래의 command 명령집단을 담을수 있게 적어준것
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public frontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 명령집단 map.put(k,v) 을 보관하는곳
		map.put("/main.do", new MainCommand()); // 처음 실행하는 페이지 - main.do를 호출하면 maincommand 메소드가 실행되게함
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청을 분석, 실행, 결과를 돌려주는곳
		request.setCharacterEncoding("utf-8"); // 한글깨짐방지
		String uri = request.getRequestURI(); // 1. uri값을 읽어온다
		String contextPath = request.getContextPath(); // 2. 그 중에서 ContextPath를 읽어온다
		String page = uri.substring(contextPath.length()); // 3. 실제 요청명(index.jsp)을 읽어내기 위해 uri에서 contextPath만큼의 길이를 뺀
															// 위치부터 헤아린다.

		Command command = map.get(page); // map안의 키 값을 command로 찾고
		String viewPage = command.exec(request, response); // 찾은 Command를 실행시켜서 그 결과를 받고

		// <view resolve start> (그 결과를 어떤 페이지에 뿌려줄것인가)
		if (!viewPage.endsWith(".do")) { // viewpage가 .do로 끝나지 않는다면
			if (viewPage.startsWith("Ajax:")) { // ajax로 시작하는지 확인
				// ajax
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print(viewPage.substring(5));// Ajax: <-(총 5자) 뒤부터 헤아려서 쓰라는거임
				return;
			} else if (!viewPage.endsWith(".tiles")) {
				viewPage = "WEB-INF/views/" + viewPage + ".jsp"; // 타일즈 적용 안하는것
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(viewPage);
		}
		// <view resolve end>

	}

}
