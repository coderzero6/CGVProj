package com.cgv.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgv.controller.action.Action;
import com.cgv.controller.action.ActionForward;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//command에 해당하는 instance를 저장하기 위한 Map

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=URF-8");
		
		//Uri command경로 읽어 들이기
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		System.out.println("RequestURI="+RequestURI);
		System.out.println("contextPath="+contextPath);
		System.out.println("command="+command);
		
		PrintWriter out = response.getWriter();
		out.print("<h3>RequestURI="+RequestURI+"</h3>");
		out.print("<h3>contextPath="+contextPath+"</h3>");
		out.print("<h3>command="+command+"</h3>");
		
		out.print("<h3>helloWorld!</h3>");
		
		//service객체 선언
		Action action = null;
		ActionForward forward = null;
		
		//command 프로퍼티 파일 읽어 들이기
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\jspProj\\CGVProj\\src\\cgv\\controller\\command.properties");
		prop.load(fis);
		fis.close();
		
		String value = prop.getProperty(command).trim(); //trim 공백제거
		
		System.out.println("prop.getProperty="+value);
		
		if(value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value, "|");
			String url_1 = st.nextToken();
			String url_2 = st.nextToken();
			System.out.println("url_1="+url_1);
			System.out.println("url_2="+url_2);
			Class url;
			try {
				url = Class.forName(url_2);
				action = (Action)url.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			forward = action.execute(request,response);
		}else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
			System.out.println("value="+value);
		}
	}
}
