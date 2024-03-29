package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements BoardService {
	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public int board_insert(BoardVO vo) {
		int dml = sql.insert("board.insert", vo);
		//첨부파일이 있는 경우 board_file 테이블에 첨부파일정보도 저장
		if( vo.getFileList() !=null  ) {
			sql.insert("board.fileInsert", vo);			
		}		
		return dml;
	}

	@Override
	public BoardPageVO board_list(BoardPageVO page) {
		page.setTotalList( sql.selectOne("board.total", page) );
		page.setList( sql.selectList("board.list", page) );
		return page;
	}

	@Override
	public BoardVO board_info(int id) {
		//방명록 정보와 첨부파일정보 조회
		BoardVO vo = sql.selectOne("board.info", id);
		vo.setFileList( sql.selectList("board.file", id) );
		return vo;
	}

	@Override
	public int board_read(int id) {
		return sql.update("board.read", id);
	}

	@Override
	public int board_update(BoardVO vo) {
		if( vo.getFileList()!=null ) {
			sql.insert("board.fileInsert", vo);
		}
		return sql.update("board.update", vo);
	}

	@Override
	public int board_delete(int id) {
		return sql.delete("board.delete", id);
	}

	@Override
	public BoardFileVO board_file_info(int id) {
		return sql.selectOne("board.fileInfo", id);
	}

	@Override
	public int board_file_delete(String removed) {
		return sql.delete("board.fileDelete", removed);
	}

	@Override
	public int board_comment_insert(BoardCommentVO vo) {
		return sql.insert("board.commentInsert", vo);
	}

	@Override
	public List<BoardCommentVO> board_comment_list(int board_id) {
		// TODO Auto-generated method stub
		return sql.selectList("board.commentList", board_id);
	}

	@Override
	public int board_comment_update(BoardCommentVO vo) {
		// TODO Auto-generated method stub
		return sql.update("board.commentUpdate", vo);
	}

	@Override
	public int board_comment_delete(int id) {
		// TODO Auto-generated method stub
		return sql.delete("board.commentDelete", id);
	}

}
