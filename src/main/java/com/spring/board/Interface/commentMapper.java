package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.CommentVO;

public interface commentMapper 
{
	public List<CommentVO> 	commentlist(CommentVO commentVO);
	public CommentVO 		commentinfo(int cno);
	public int commentwrite(CommentVO commentVO);
	public int commentcount(CommentVO commentVO);
	public int commentdelete(int bno);
	public int commentdeleteone(int cno);
	public int commentdeleteori(int cno);
	public int commentmody(CommentVO commentVO);
	//계층형 구현용
	//글 작성시 그룹 번호 설정
	public CommentVO commentlast();
	//원글 번호 번호 설정
	public int commentoriup(CommentVO commentVO);
	//원글 답글 순서값 설정용
	public CommentVO commentgrdmax(CommentVO commentVO);
}
