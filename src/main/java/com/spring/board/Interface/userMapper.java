package com.spring.board.Interface;

import com.spring.board.VO.UserVO;

public interface userMapper 
{
	public int userjoin(UserVO vo);
	public UserVO userinfo(String id);
	
}
