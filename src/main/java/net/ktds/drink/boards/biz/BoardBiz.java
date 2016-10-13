package net.ktds.drink.boards.biz;

import net.ktds.drink.boards.vo.BoardListVO;
import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.boards.vo.SearchBoardVO;

public interface BoardBiz {
	
	public boolean addBoard(BoardVO board);
	
	public boolean updateBoard(BoardVO board);

	public BoardListVO getBoardListsOf(SearchBoardVO searchBoard);

	public BoardVO getBoardAt(String boardId);
	
	public String getFileNameOfBoardBy(String boardId);
	
	public BoardVO getBoardForModify(String boardId);
	
	public boolean removeBoard(String boardId);
	
	public boolean updateHitCount(String boardId);
	
	public boolean updateRecommendCount(String boardId);
	
}
