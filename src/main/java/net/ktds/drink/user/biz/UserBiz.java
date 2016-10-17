package net.ktds.drink.user.biz;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.user.vo.UserVO;


/**
 * 
 * @author 206-017
 *
 */



public interface UserBiz {

	public boolean isExsistUserEmail(String userEmail);

	public boolean isExsistUserNickname(String userNickname);

	public boolean signUpUser(UserVO user);

	public boolean signIn(UserVO user, HttpServletRequest request);

	public List<UserVO> getListUserInfo(SearchUserVO searchUserVO);
	
	public boolean deleteUserInfo(String userId);

}
