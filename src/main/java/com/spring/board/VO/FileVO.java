package com.spring.board.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileVO 
{
	private int fno;
	private int bno;
	private int uno;
	private String fname;
	private String ex;
	private String path;
	private long fsize;
}
