package net.ktds.drink.boards.dao;

import java.util.List;

import net.ktds.drink.boards.vo.BoardVO;
import net.ktds.drink.boards.vo.SearchBoardVO;

public interface BoardDao {
	
	public int insert(BoardVO board);
	
	public List<BoardVO> selectBoards(SearchBoardVO searchBoard);
	
	public BoardVO selectBoard(String boardId);
	
	public int getCountOfBoards(SearchBoardVO searchBoard);

	public int updateBoard(BoardVO board);
	
	public int deleteBoard(String boardId);
	
	public int updateHitCount(String boardId);
	
	public int updateRecommendCount(String boardId);
	
}
