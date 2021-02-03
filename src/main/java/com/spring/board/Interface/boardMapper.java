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
	//�� ����
	public int boardmody(BoardVO vo);
	//�� ����
	public int boarddelete(int bno);
	//��� ������� ��۱��� ����
	public int boarddeleteori(int bno);
	//��ȸ�� ����
	public int boardhit(int bno);
	//������ ������
	//�� �ۼ��� �׷� ��ȣ ����
	public BoardVO boardlast();
	//���� �� ��ȣ ����
	public int boardanup(BoardVO vo);
	//���� ��ȣ ��ȣ ����
	public int boardoriup(BoardVO vo);
	//���� ��� ������ ������
	public BoardVO boardgrdmax(BoardVO vo);
	
}
