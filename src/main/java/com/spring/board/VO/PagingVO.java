package com.spring.board.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO 
{
		
	private int nowPage, 	//현재 페이지 최초 1
				startPage,	//다섯개중에 첫번째 페이지 값
				endPage,	//다섯개중에 마지막 페이지 값
				total,		//전체 글 개수
				cntPerPage, //페이지당 글 개수 기본값 5
				lastPage, 	//마지막 페이지
				start, 		//sql용 limit 시작 값
				end;		//sql용 limit 끝값

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
		//컨트롤러를 통해 얻어온 글의 총 갯수를 5로 나누고 올림한다.
		//글이 51개라면 11페이지가 된다.
	}
	
	public void calcStartEndPage(int nowPage, int cntPage) 
	{
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		//현재 페이지 (ex 6) 를 5로 나누고 올림한 값에 다시 5를 곱한다. 6 / 5 = 2 * 5 = 10
		if (getLastPage() < getEndPage()) 
			//전체 마지막 페이지 < 현재 마지막 페이지 이면
		{
			setEndPage(getLastPage());
			//현재 마지막 페이지 값을 전체 마지막 페이지 값으로 설정한다.
		}
		setStartPage(getEndPage() - cntPage + 1);
		//시작 페이지의 값은 현재 마지막 페이지 값에서 5를 빼고 1을 더한 값이다.
		if (getStartPage() < 1)
		//시작페이지가 0이라면 1로 설정해준다.
		{
			setStartPage(1);
		}
	}
	
	public void calcStartEnd(int nowPage, int cntPerPage) {
		//현재 페이지와 페이지당 글 개수를 받아온다. 1, 5
		setEnd(nowPage * cntPerPage);
		//limit 두번째 값은 1 * 5
		setStart(getEnd() - cntPerPage);
		////limit 첫번째 값은  5 - 5
	}
}
