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
	//������ ������
	//�� �ۼ��� �׷� ��ȣ ����
	public CommentVO commentlast();
	//���� ��ȣ ��ȣ ����
	public int commentoriup(CommentVO commentVO);
	//���� ��� ������ ������
	public CommentVO commentgrdmax(CommentVO commentVO);
}
