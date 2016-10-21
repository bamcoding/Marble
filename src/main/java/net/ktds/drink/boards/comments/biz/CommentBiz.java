package net.ktds.drink.boards.comments.biz;

import java.util.List;

import net.ktds.drink.boards.comments.vo.CommentVO;

public interface CommentBiz {

	public boolean addComment(CommentVO comment);
	
	public List<CommentVO> getCommentsOf(String boardId);
	
	public CommentVO getCommentForModify(String commentId);
	
	public boolean modifyComment(CommentVO comment);
	
	public boolean removeComment(String commentId);
	
}
