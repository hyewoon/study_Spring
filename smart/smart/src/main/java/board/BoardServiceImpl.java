package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired private BoardDAO dao;
	
	@Override
	public int board_insert(BoardVO vo) {
		return dao.board_insert(vo);
	}

	@Override
	public BoardPageVO board_list(BoardPageVO page) {
		return dao.board_list(page);
	}

	@Override
	public BoardVO board_info(int id) {
		return dao.board_info(id);
	}

	@Override
	public int board_read(int id) {
		return dao.board_read(id);
	}

	@Override
	public int board_update(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.board_update(vo);
	}

	@Override
	public int board_delete(int id) {
		// TODO Auto-generated method stub
		return dao.board_delete(id);
	}

	@Override
	public BoardFileVO board_file_info(int id) {
		return dao.board_file_info(id);
	}

	@Override
	public int board_file_delete(String removed) {
		// TODO Auto-generated method stub
		return dao.board_file_delete(removed);
	}

	@Override
	public int board_comment_insert(BoardCommentVO vo) {
		// TODO Auto-generated method stub
		return dao.board_comment_insert(vo);
	}


	@Override
	public int board_comment_update(BoardCommentVO vo) {
		// TODO Auto-generated method stub
		return dao.board_comment_update(vo);
	}

	@Override
	public int board_comment_delete(int id) {
		// TODO Auto-generated method stub
		return dao.board_comment_delete(id);
	}

	@Override
	public List<BoardCommentVO> board_comment_list(int board_id) {
		// TODO Auto-generated method stub
		return dao.board_comment_list(board_id);
	}

	

}
