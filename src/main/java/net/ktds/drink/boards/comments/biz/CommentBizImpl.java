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
	public CommentVO getCommentForModify(String commentId) {
		return commentDao.selectComment(commentId);
	}
	
	@Override
	public boolean modifyComment(CommentVO comment) {
		CommentVO originalComment = commentDao.selectComment(comment.getCommentId());
		
		if (originalComment.getCommentContent().equals(comment.getCommentContent())){
			comment.setCommentContent(null);
			return true;
		}
		
		return commentDao.updateComment(comment) > 0;
	}

	@Override
	public boolean removeComment(String commentId) {
		return commentDao.deleteComment(commentId) > 0;
	}

	@Override
	public String getTime() {
		return commentDao.getTime();
	}

	@Override
	public String getNewCommentId() {
		return commentDao.getNewCommentId();
	}

}
