package com.spring.board.VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO 
{
	private int uno;
	private Date udate;
	private String id;
	private String pw;
	private String name;
	
}
