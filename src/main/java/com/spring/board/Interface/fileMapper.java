package com.spring.board.Interface;

import com.spring.board.VO.FileVO;

public interface fileMapper 
{
	public int fileinsert(FileVO fileVO);
	public int filedelete(int bno);
	public int filedeleteori(int bno);
	public FileVO fileselect(int bno);
	
}
