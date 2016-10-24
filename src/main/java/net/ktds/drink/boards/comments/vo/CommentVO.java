package net.ktds.drink.boards.comments.vo;

import net.ktds.drink.user.vo.UserVO;

public class CommentVO {

	private String commentId;
	private String boardId;
	private String commentContent;
	private String userId;
	private String createdDate;
	private String modifyDate;
	private String parentCommentId;
	
	private UserVO userVO;
	
	public CommentVO() {
		super();
		userVO = new UserVO();
	}
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	@Override
	public String toString() {
		return commentId + "," + commentContent
				+ "," + createdDate + "," + userVO.getUserNickname();
	}
	
	
}
