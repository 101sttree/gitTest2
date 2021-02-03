package com.spring.board.Controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spring.board.Interface.userMapper;
import com.spring.board.VO.UserVO;

@Controller
public class userController 
{
	@Inject
	userMapper mapper;
//=====================================================================================
//회원가입
//=====================================================================================	
	
	@PostMapping(value = "/joinok")
	public String userjoin(Model model, UserVO vo)
	{
		int join = mapper.userjoin(vo);
		return "redirect:/login";
	}
//=====================================================================================
//아이디 중복확인
//=====================================================================================	
	
	@PostMapping(value = "/idck")
	public void idck
	(
		Model model,
		HttpServletRequest request,
		HttpServletResponse response,
		String id
	)
	throws Exception
	{
		PrintWriter pw 		= response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		
		try 
		{
			UserVO idck = mapper.userinfo(id);
			if(idck == null)
			{
				jObject.add("result", gson.toJsonTree("ok"));
			}
			if(idck != null)
			{
				jObject.add("result", gson.toJsonTree("no"));
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
			pw.write(gson.toJson(jObject));
		}
	}
//=====================================================================================
//로그인
//=====================================================================================	
	
	@PostMapping(value = "/login")
	public void userinfo
	(
		Model model, 
		HttpServletRequest 	request,
		HttpServletResponse response,
		String id,
		String Pw
			
	) 
	throws Exception
	{
		PrintWriter pw 		= response.getWriter();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		JsonObject 	jObject 		= new JsonObject();
		HttpSession Session = request.getSession();
		
		try 
		{
			UserVO login = mapper.userinfo(id);
			if(login != null)
			{
				if(login.getPw().equals(Pw))
				{
					jObject.add("result", gson.toJsonTree("loginok"));
					Session.setAttribute("user", login);
					Session.setAttribute("id", login.getId());
				}
				if (!login.getPw().equals(Pw))
				{
					jObject.add("result", gson.toJsonTree("pwfail"));
				}
			}
			if(login == null) 
			{
				jObject.add("result", gson.toJsonTree("idfail"));
			}
			jObject.add("check", gson.toJsonTree("success"));
		}
		catch (Exception e) 
		{
			jObject = new JsonObject();
			jObject.add("check", gson.toJsonTree("fail"));
			jObject.add("error", gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pw.write(gson.toJson(jObject));
		}
	}
//=====================================================================================
//로그아웃
//=====================================================================================		
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
}
