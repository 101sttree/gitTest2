package com.spring.board.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO 
{
		
	private int nowPage, 	//���� ������ ���� 1
				startPage,	//�ټ����߿� ù��° ������ ��
				endPage,	//�ټ����߿� ������ ������ ��
				total,		//��ü �� ����
				cntPerPage, //�������� �� ���� �⺻�� 5
				lastPage, 	//������ ������
				start, 		//sql�� limit ���� ��
				end;		//sql�� limit ����

	private int cntPage = 5;
	
	public PagingVO() {
	}
	public PagingVO(int total, int nowPage, int cntPerPage) {
		setNowPage		(nowPage);
		setCntPerPage	(cntPerPage);
		setTotal		(total);
		calcLastPage	(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd	(getNowPage(), getCntPerPage());
	}
	public void calcLastPage(int total, int cntPerPage) 
	{
		setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
		//��Ʈ�ѷ��� ���� ���� ���� �� ������ 5�� ������ �ø��Ѵ�.
		//���� 51����� 11�������� �ȴ�.
	}
	
	public void calcStartEndPage(int nowPage, int cntPage) 
	{
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		//���� ������ (ex 6) �� 5�� ������ �ø��� ���� �ٽ� 5�� ���Ѵ�. 6 / 5 = 2 * 5 = 10
		if (getLastPage() < getEndPage()) 
			//��ü ������ ������ < ���� ������ ������ �̸�
		{
			setEndPage(getLastPage());
			//���� ������ ������ ���� ��ü ������ ������ ������ �����Ѵ�.
		}
		setStartPage(getEndPage() - cntPage + 1);
		//���� �������� ���� ���� ������ ������ ������ 5�� ���� 1�� ���� ���̴�.
		if (getStartPage() < 1)
		//������������ 0�̶�� 1�� �������ش�.
		{
			setStartPage(1);
		}
	}
	
	public void calcStartEnd(int nowPage, int cntPerPage) {
		//���� �������� �������� �� ������ �޾ƿ´�. 1, 5
		setEnd(nowPage * cntPerPage);
		//limit �ι�° ���� 1 * 5
		setStart(getEnd() - cntPerPage);
		////limit ù��° ����  5 - 5
	}
}
