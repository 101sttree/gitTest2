package com.spring.board.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spring.board.Interface.commentMapper;
import com.spring.board.VO.BoardVO;
import com.spring.board.VO.CommentVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.UserVO;

@Controller
public class commentController 
{
	@Inject
	commentMapper mapper;
//=====================================================================================	
//댓글 불러오기
//=====================================================================================		
	
	@GetMapping(value = "/commentlist")
	public void commentlist
	(
		Model model,
		CommentVO 	commentVO,
		PagingVO 	pagingVO,
		String 		nowPage,
		String 		cntPerPage,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		
		try 
		{
			int total = mapper.commentcount(commentVO);
			if (nowPage == null && cntPerPage == null) 
			{
				nowPage = "1";
				cntPerPage = "10";
			}
			if (nowPage == null) 
			{
				nowPage = "1";
			}
			if (cntPerPage == null) 
			{ 
				cntPerPage = "10";
			}
			pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			//받아온 값을 페이징객체에 넣어준다.
			commentVO.setStart(pagingVO.getStart());
			//댓글 목록을 불러오는데 사용될 start 값을 페이징객체에서 얻어온다.
			List<CommentVO> list = mapper.commentlist(commentVO);
			UserVO userVO = (UserVO)session.getAttribute("user");
			if(list.size() > 0)
			{
				jObject.add("list", 	gson.toJsonTree(list));
				jObject.add("paging", 	gson.toJsonTree(pagingVO));
				jObject.add("user", 	gson.toJsonTree(userVO));
				jObject.add("check", 	gson.toJsonTree("yes"));
			}
			if(list.size() == 0)
			{
				jObject.add("check", gson.toJsonTree("no"));
			}
		} 
		catch(Exception e) 
		{
			jObject = new JsonObject();
			jObject.add("cheack", 	gson.toJsonTree("fail"));
			jObject.add("erroe", 	gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pWriter.write(gson.toJson(jObject));
		}
	}
//=====================================================================================	
//댓글 작성	
//=====================================================================================		
	
	@PostMapping(value = "/commentwrite")
	public void commentwrite
	(
		Model model,
		CommentVO commentVO,
		HttpServletRequest request,
		HttpServletResponse response	
	)
	{
		if(commentVO.getCno() > 0)
		{	
			CommentVO commentgrdmax = mapper.commentgrdmax(commentVO);
			commentVO.setGroupord(commentgrdmax.getGroupord()+1);
			commentVO.setOrigino(commentgrdmax.getOrigino());
			commentVO.setGrouplayer(1);
		}
		
		int commentwrite = mapper.commentwrite(commentVO);
		CommentVO commentlast = mapper.commentlast();
		if(commentVO.getOrigino() == 0)
		{
			mapper.commentoriup(commentlast);
		}
	}
//=====================================================================================
//댓글 수정
//=====================================================================================	
	
	@PostMapping(value = "/commentmody")
	public void commentmody
	(
		CommentVO commentVO,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		UserVO 		userVO 	= (UserVO)session.getAttribute("user");
		try 
		{
			if(userVO.getUno() == commentVO.getUno())
			{
				int 		cmody 		= mapper.commentmody(commentVO);
				CommentVO 	commentinfo = mapper.commentinfo(commentVO.getCno());
				jObject.add("check", gson.toJsonTree("ok"));
				jObject.add("cinfo", gson.toJsonTree(commentinfo));
			}
			if(userVO.getUno() != commentVO.getUno())
			{
				CommentVO 	commentinfo = mapper.commentinfo(commentVO.getCno());
				jObject.add("check", gson.toJsonTree("no"));
				jObject.add("cinfo", gson.toJsonTree(commentinfo));
			}
		}
		catch (Exception e) 
		{
			jObject = new JsonObject();
			jObject.add("cheack", 	gson.toJsonTree("fail"));
			jObject.add("erroe", 	gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pWriter.write(gson.toJson(jObject));
		}
		
		
		
	}
//=====================================================================================
//댓글 삭제
//=====================================================================================	
	
	@PostMapping(value = "/commentdelete")
	public void commentdeleteone
	(
		int cno,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		UserVO 		userVO 	= (UserVO)session.getAttribute("user");
		CommentVO 	commentVO = mapper.commentinfo(cno);
		try 
		{
			if(userVO.getUno() == commentVO.getUno())
			{
				if(commentVO.getGrouplayer() == 1)
				{
					int cdelete = mapper.commentdeleteone(cno);
				}
				if(commentVO.getGrouplayer() == 0)
				{
					int cdeleteori = mapper.commentdeleteori(cno);
				}
				jObject.add("check", gson.toJsonTree("ok"));
			}
			if(userVO.getUno() != commentVO.getUno())
			{
				jObject.add("check", gson.toJsonTree("no"));
			}
		}
		catch (Exception e) 
		{
			jObject = new JsonObject();
			jObject.add("cheack", 	gson.toJsonTree("fail"));
			jObject.add("erroe", 	gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pWriter.write(gson.toJson(jObject));
		}
		
		
		
	}
	

}
