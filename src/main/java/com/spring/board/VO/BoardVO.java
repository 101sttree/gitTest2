package com.spring.board.VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO 
{
	private int bno;
	private int uno;
	private int hit;
	private int origino;
	private int groupord;
	private int grouplayer;
	private String writer;
	private String title;
	private String btext;
	private Date bdate;
}
