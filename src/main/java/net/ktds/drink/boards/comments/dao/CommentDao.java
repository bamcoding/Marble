package net.ktds.drink.boards.comments.dao;

public interface CommentDao {

<<<<<<< HEAD
=======
	public int insert(CommentVO comment);
	
	public List<CommentVO> selectComments(String boardId);
	
	public CommentVO selectComment(String commentId);
	
	public int updateComment(CommentVO comment);
	
	public int deleteComment(String commentId);
	
>>>>>>> 3은주누나꺼_댓글
}
