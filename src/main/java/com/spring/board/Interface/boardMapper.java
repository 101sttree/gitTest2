package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.BoardVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.SearchVO;

public interface boardMapper 
{
	public List<BoardVO> boardlist(SearchVO vo);
	public BoardVO boarddetail(int bno);
	public BoardVO boarddetailan(int bno);
	
	public int boardwrite(BoardVO vo);
	public int boardcount(SearchVO vo);
	//글 수정
	public int boardmody(BoardVO vo);
	//글 삭제
	public int boarddelete(int bno);
	//답글 있을경우 답글까지 삭제
	public int boarddeleteori(int bno);
	//조회수 증가
	public int boardhit(int bno);
	//계층형 구현용
	//글 작성시 그룹 번호 설정
	public BoardVO boardlast();
	//기존 글 번호 증가
	public int boardanup(BoardVO vo);
	//원글 번호 번호 설정
	public int boardoriup(BoardVO vo);
	//원글 답글 순서값 설정용
	public BoardVO boardgrdmax(BoardVO vo);
	
}
