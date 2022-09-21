package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.UserDto;


@WebServlet("/SimpleJsonListServlet")
public class SimpleJsonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비동기 요청에 대한 처리
		// response : html X
		// response : data only : xml, json ... => json
		
		// request : client 의 요청
		// response : client 에게 응답
		
		// 클라이언트가 보낸 데이터를 확인 
		String userId = request.getParameter("userId"); // 일반적으로는 이름 맞춰주기
		String msg = request.getParameter("msg");
		System.out.println("userId : " + userId + "   / msg : " + msg);

		// 클라이언트에게 보내는 데이터 준비, 전송
		ArrayList<UserDto> userList = new ArrayList<>();
		userList.add(new UserDto(1111, "홍길동", "hong@mail.com"));
		userList.add(new UserDto(2222, "이길동", "lee@mail.com"));
		userList.add(new UserDto(3333, "삼길동", "sam@mail.com"));
		
		// java object => json
		// gson library jar를 tomcat/lib 에 추가
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userList); // jsonStr : userList를 표현한 JSON 문자열
		
		
		//cors 오류 해결
		response.addHeader("Access-Control-Allow-Origin", "*"); // VSCode live server CORS
		
		System.out.println(jsonStr);
		response.setContentType("text/html; charset=utf-8"); 
		response.getWriter().write(jsonStr); // 클라이언트에게 문자열 전송
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
