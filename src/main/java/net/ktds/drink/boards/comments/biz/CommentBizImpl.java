package net.ktds.drink.boards.comments.biz;

import java.util.List;

import net.ktds.drink.boards.comments.dao.CommentDao;
import net.ktds.drink.boards.comments.dao.CommentDaoImpl;
import net.ktds.drink.boards.comments.vo.CommentVO;
import net.ktds.drink.support.DaoSupport;

public class CommentBizImpl extends DaoSupport implements CommentBiz{

	private CommentDao commentDao;
	
	public CommentBizImpl(){
		commentDao = new CommentDaoImpl();
	}
	
	@Override
	public boolean addComment(CommentVO comment) {
		return commentDao.insert(comment) > 0;
	}

	@Override
	public List<CommentVO> getCommentsOf(String boardId) {
		return commentDao.selectComments(boardId);
	}

	@Override
	public boolean modifyComment(CommentVO comment) {
		return commentDao.updateComment(comment) > 0;
	}

	@Override
	public boolean removeComment(String commentId) {
		return commentDao.deleteComment(commentId) > 0;
	}

}
