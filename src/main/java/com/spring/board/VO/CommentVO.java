package com.spring.board.VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO 
{
	private int cno;
	private int	bno;
	private int uno;
	private int start;
	private int origino;
	private int groupord;
	private int grouplayer;
	private String cwriter;
	private String ctext;
	private Date cdate;
}
