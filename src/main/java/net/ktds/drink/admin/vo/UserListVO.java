package net.ktds.drink.admin.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.user.vo.UserVO;

public class UserListVO {
	
	private List<UserVO> users;
	private Pager pager;
	
	public List<UserVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserVO> users) {
		this.users = users;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
