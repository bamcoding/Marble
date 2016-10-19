package net.ktds.drink.user.dao;

import java.util.List;

import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.user.vo.UserVO;

/**
 * 다음과 같은 추상 메소드를 가진다
 * 1. 로그인
 * 2. 회원가입
 * 3. 포인트 적립
 * 4. 중복회원 체크
 * @author 206-017
 *
 */


public interface UserDao {

	public int countUserEmail(String userEmail);

	public int countUserNickname(String userNickname);

	public int signUpUser(UserVO user);

	public UserVO getUserBy(UserVO user);

	public List<UserVO> getListUserInfo(SearchUserVO searchUserVO);

	public int deleteUserInfo(String userId);
	
	public int getCountUsers(SearchUserVO searchUser);
	
	public int userPasswordReset(String userId);

}
