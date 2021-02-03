package com.spring.board.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchVO 
{
	private String searchType;
	private String searchText;
	private int start;
	private int end;
}
