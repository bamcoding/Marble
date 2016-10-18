package net.ktds.drink.user.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ktds.drink.admin.vo.SearchUserVO;
import net.ktds.drink.constants.Session;
import net.ktds.drink.support.DaoSupport;
import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.support.pager.PagerFactory;
import net.ktds.drink.user.dao.UserDao;
import net.ktds.drink.user.dao.UserDaoImpl;
import net.ktds.drink.user.vo.UserVO;

public class UserBizImpl extends DaoSupport implements UserBiz {
	
	private UserDao userDao;
	
	public UserBizImpl(){
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean isExsistUserEmail(String userEmail) {
		return userDao.countUserEmail(userEmail) > 0;
	}

	@Override
	public boolean isExsistUserNickname(String userNickname) {
		return userDao.countUserNickname(userNickname) > 0;
	}

	@Override
	public boolean signUpUser(UserVO user) {
		return userDao.signUpUser(user) > 0;
	}

	@Override
	public boolean signIn(UserVO user, HttpServletRequest request) {
		UserVO userInfo = userDao.getUserBy(user);
		//System.out.println(userInfo.getUserId());
		if ( userInfo != null && userInfo.getUserId() != null && userInfo.getUserId().length() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute(Session.USER_INFO, userInfo);
			return true;
		}
		return false;
	}

	@Override
	public List<UserVO> getListUserInfo(SearchUserVO searchUserVO) {
		int totalCount = userDao.getCountUsers(searchUserVO);
		Pager pager = PagerFactory.getPager(true); //페이저 불러오기
		pager.setTotalArticleCount(totalCount); //토탈카운트 할당하기
		pager.setPageNumber(0); // 첫페이지:자동계산됨
		int startRowNumber = pager.getStartArticleNumber();
		int endRowNumber = pager.getEndArticleNumber();
		
		return userDao.getListUserInfo();
	}

	@Override
	public boolean deleteUserInfo(String userId) {
		return userDao.deleteUserInfo(userId) > 0;
	}
	
	

}
