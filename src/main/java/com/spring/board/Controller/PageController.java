package com.spring.board.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spring.board.VO.BoardVO;
import com.spring.board.VO.UserVO;

@Controller
public class PageController 
{
	
	
	//로그인 화면 이동
	@GetMapping(value = "/login")
	public String login() 
	{
		return "user/login";
	}
	
	//회원가입 화면 이동
	@GetMapping(value = "/join")
	public String join() 
	{
		return "user/join";
	}
	
	//글 쓰기 창
	@GetMapping(value = "/writego")
	public String writego(Model model)
	{
		return "board/write";
	}
	
	//답글 쓰기창
	@GetMapping(value = "/anwritego")
	public String anwritego(Model model, BoardVO vo)
	{
		model.addAttribute("vo", vo);
		return "board/write";
	}
	
	//글 쓰기 로그인 체크
	@GetMapping(value = "/loginCheck")
	public void loginCheck
	(
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		HttpSession session = request.getSession();
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		try 
		{
			UserVO 	vo 		= (UserVO)session.getAttribute("user");
			if(vo != null)
			{
				jObject.add("check", gson.toJsonTree("loginok"));
			}
			if(vo == null) 
			{
				jObject.add("check", gson.toJsonTree("loginno"));
			}
		} 
		catch (Exception e) 
		{
			jObject = new JsonObject();
			jObject.add("check", gson.toJsonTree("fail"));
			jObject.add("error", gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pWriter.write(gson.toJson(jObject));
		}
		
		
	}
	
	
	
	
}
