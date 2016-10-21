package net.ktds.drink.boards.comments.dao;

import java.util.List;

import net.ktds.drink.boards.comments.vo.CommentVO;

public interface CommentDao {

	public int insert(CommentVO comment);
	
	public List<CommentVO> selectComments(String boardId);
	
	public CommentVO selectComment(String commentId);
	
	public int updateComment(CommentVO comment);
	
	public int deleteComment(String commentId);
	
	public String getTime();
	
	public String getNewCommentId();
	
}
