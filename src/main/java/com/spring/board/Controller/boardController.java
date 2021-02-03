package com.spring.board.Controller;

import java.io.File;
import java.lang.invoke.VolatileCallSite;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.Interface.boardMapper;
import com.spring.board.Interface.commentMapper;
import com.spring.board.Interface.fileMapper;
import com.spring.board.VO.BoardVO;
import com.spring.board.VO.FileVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.SearchVO;
import com.spring.board.VO.UserVO;

@Controller

public class boardController

{
	@Inject	
	boardMapper mapper;
	@Inject
	commentMapper cmapper;
	@Inject
	fileMapper fmapper;
	
	
	  private static final String UPLOAD_PATH =
	  "C:\\Users\\cube\\Documents\\GitHub\\SpringBoard\\SpringBoard\\src\\main\\webapp\\resources\\file";
	 
	/*
	 * private static final String UPLOAD_PATH =
	 * "C:\\Users\\hyosin\\Documents\\GitHub\\SpringBoard\\SpringBoard\\src\\main\\webapp\\resources\\file";
	 */
//=====================================================================================
//�� �ҷ�����
//=====================================================================================	
	  
	@GetMapping(value = "/")
	public String boardlist
	(
		Model model,
		HttpServletRequest request,
		HttpServletResponse response,
		PagingVO pagingVO,
		SearchVO searchVO,
		String nowPage,
		String cntPerPage,
		String searchType,
		String searchText
	) 
	{
		searchVO.setSearchType(searchType);
		searchVO.setSearchText(searchText); 
		int total = mapper.boardcount(searchVO);
		System.out.print("");
		String xString = null;
		
		//���� ������ �� �������� �� ���� ����
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
		
		pagingVO = new PagingVO(total, 	Integer.parseInt(nowPage),
										Integer.parseInt(cntPerPage));
		
		searchVO.setStart(pagingVO.getStart());
		
		List<BoardVO> list = mapper.boardlist(searchVO);
		
		model.addAttribute("paging", pagingVO);
		model.addAttribute("list", list);
		
		return "main";
	}
//=====================================================================================
//�� �� ����
//=====================================================================================	
	
	@GetMapping(value = "/board/detail")
	public ModelAndView detail
	(
		HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session,
		Model model,
		int bno,
		@RequestParam HashMap<Object, Object> params,
		ModelAndView mv
	) {
		
		UserVO 		userVO 		= (UserVO)session.getAttribute("user");
		Cookie[] 	reqCookie 	= request.getCookies();
		Cookie 		nullCookie 	= null;
		
		if(reqCookie != null && reqCookie.length > 0 && userVO != null)
		{
			for (int i = 0; i < reqCookie.length; i++) 
			{
				if(reqCookie[i].getName().equals("cookie" + userVO.getUno() + bno))
				{
					nullCookie = reqCookie[i];
				}
			}
		}
		if(reqCookie != null && reqCookie.length > 0 && userVO == null)
		{
			for (int i = 0; i < reqCookie.length; i++) 
			{
				if(reqCookie[i].getName().equals("cookie" + bno))
				{
					nullCookie = reqCookie[i];
				}
			}
		}
		if(userVO != null && nullCookie == null)
		{
			Cookie cookie = 
			new Cookie("cookie"+ userVO.getUno() + bno, "cookie"+ userVO.getUno() + bno);
			cookie.setMaxAge(60*60*12);
			response.addCookie(cookie);
			int boardhit = mapper.boardhit(bno);
			
			if(boardhit > 0)
			{
				System.out.println("��ȸ�� ���� ����");
			}
			if(boardhit <= 0)
			{
				System.out.println("��ȸ�� ���� ����");
			}
		}
		if(userVO == null && nullCookie == null)
		{
			Cookie cookie = new Cookie("cookie" + bno, "cookie" + bno);
			cookie.setMaxAge(60*60*12);
			response.addCookie(cookie);
			int boardhit = mapper.boardhit(bno);
			
			if(boardhit > 0)
			{
				System.out.println("��ȸ�� ���� ����");
			}
			if(boardhit <= 0)
			{
				System.out.println("��ȸ�� ���� ����");
			}
		}
		
		
		BoardVO vo 		= mapper.boarddetail(bno);
		FileVO fileVO 	= fmapper.fileselect(bno);
		mv.addObject("vo", vo);
		mv.addObject("fvo", fileVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
//=====================================================================================
//���� �ٿ�ε� Ŭ���� ���� ��Ʈ�ѷ�
//=====================================================================================		
	
	@RequestMapping("/common/download")
	public ModelAndView download
	(
		@RequestParam HashMap<Object, Object> params,
		ModelAndView mv
	)
	{
		//���� ������ ��´�.
		String fileName = (String) params.get("fileName");
		String fullPath = UPLOAD_PATH + "/" + fileName;
		File file = new File(fullPath);
		
		
		//�ش� ��� ���� ������ ������.
		mv.setViewName("downloadView");
		mv.addObject("downloadFile", file);
		return mv;
	}
	
//=====================================================================================
//�� �ۼ�
//=====================================================================================		
	@PostMapping(value = "/board/write")
	public String boardwrite
	(
		Model model,
		BoardVO vo,
		MultipartHttpServletRequest mtfRequest
		//ȭ�鿡�� �޾ƿ� ������ ������ �ִ� ��ü�̴�.
	) 
	{
	    try 
	    {
			if(vo.getBno() > 0)
			//��� �ۼ��� ���
			{	
				//����� ���
				if(vo.getGrouplayer() != 0)
				{
					//���� �۵� ������ 1 ����
					mapper.boardanup(vo);
					//������ ���� �θ�ۼ��� + 1 �� ���� 
					vo.setGroupord(vo.getGroupord()+1);
					//�θ���� ����̱� ������ ���� + 1
					vo.setGrouplayer(vo.getGrouplayer() + 1);
					System.out.println("����� ���" + vo.toString());
				}
				//������ ���
				if(vo.getGrouplayer() == 0)
				{
					//���� �ۼ��� ������ ���� �������� �����´�.
					BoardVO	boardgrdmax = mapper.boardgrdmax(vo);
					//���� �ۼ��Ǵ� ���� �������� ���������� �������� + 1 ������ ����
					vo.setGroupord(boardgrdmax.getGroupord()+1);
					//������ ����̱� ������ ���� 1�� ����
					vo.setGrouplayer(1);
					System.out.println("������ ���" + vo.toString());
				}
			}
			//�Ϲ� �� �ۼ��� ���
			int 		write 		= mapper.boardwrite(vo);
			//�Ϲ� �� ������ ���� �׷��ȣ(origino)�� �۹�ȣ�� ����
			BoardVO 	boardlast 	= mapper.boardlast(); 
			if(vo.getOrigino() == 0)
			{
				mapper.boardoriup(boardlast);
			}
			//�� �ۼ��� ��ϵ� ���� ������ ���� ��ü ����
			MultipartFile mf = mtfRequest.getFile("file");
			if(mf != null) 
			{ 
				//���� ���� ���� �� �Է� 
				String 	originFileName 	= mf.getOriginalFilename(); 
				long 	fileSize 		= mf.getSize(); 
				String 	safeFile 		= UPLOAD_PATH + "\\" + originFileName; 
				FileVO 	fileVO 			= new FileVO();
				//���� VO ��ü�� ������ ������ ��´�.
				fileVO. setBno(boardlast.getBno());
				fileVO. setUno(vo.getUno());
				fileVO. setFsize(mf.getSize());
				fileVO. setFname(mf.getOriginalFilename());
				fileVO. setPath(UPLOAD_PATH); 
				int index = originFileName.lastIndexOf('.');
				String hwak = originFileName.substring(index); 
				fileVO. setEx(hwak);
				//���� ��ο� ���� ����
				mf.transferTo(new File(safeFile));
				//DB�� ���� ���� ����
				int fileinsert = fmapper.fileinsert(fileVO); 
			}
		  } 
		  catch (IllegalStateException e) 
		  {
			   e.fillInStackTrace();
		  }
		  catch (Exception e) 
		  {
			  System.out.println(e);
		  }
		return "redirect:/";
	}
	
//=====================================================================================
//�� ����
//=====================================================================================		
	@GetMapping(value = "/board/delete")
	public String boarddelete(Model model, int bno) {
		
		
		//�ش� �ۿ� ����� �ִ��� Ȯ��
		BoardVO boardinfoan = mapper.boarddetailan(bno);
		
		
		//����� �޸� �� ����
		//���ο��� �޸� ��۰� ��۵��� ���� �� ��� ����
		if(boardinfoan != null)
		{
			int filedeleteori 	= fmapper.filedeleteori(bno);
			int cdelete 		= cmapper.commentdelete(bno);
			int boarddeleteori 	= mapper.boarddeleteori(bno);
		}
		
		//����� ���� �� ����
		if(boardinfoan == null)
		{
			int fdelet 	= fmapper.filedelete(bno);
			int cdelete = cmapper.commentdelete(bno);
			int delete 	= mapper.boarddelete(bno);
		}
		
		return "redirect:/";
	}
//=====================================================================================
//�� ���� ȭ�� �̵�
//=====================================================================================	
	
	@GetMapping(value = "/mody")
	public String mody(Model model, int bno) {
		BoardVO vo = mapper.boarddetail(bno);
		model.addAttribute("vo", vo);
		return "board/mody";
	}
//=====================================================================================
//�� ����
//=====================================================================================	
	
	@PostMapping(value = "/board/mody")
	public String boardmody(Model model, BoardVO vo) {
		int mody = mapper.boardmody(vo);
		BoardVO boardVO = mapper.boarddetail(vo.getBno());
		model.addAttribute("vo", boardVO);
		return "board/detail";
	}
}
