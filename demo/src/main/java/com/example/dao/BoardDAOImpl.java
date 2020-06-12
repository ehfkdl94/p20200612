package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.BoardVO;

@Service
@Transactional
public class BoardDAOImpl implements BoardDAO {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private SqlSessionFactory sqlFatory = null;
	
	@Override
	public int insertBoard(BoardVO obj) {
		// TODO Auto-generated method stub
		return sqlFatory.openSession().insert("Board.insertBoard", obj);
	}

	@Override
	public List<BoardVO> selectBoard(HashMap<String, Object> map) {
		return sqlFatory.openSession().selectList("Board.boardlist", map);
	}

	@Override
	public BoardVO selectBoardOne(int no) {
		return sqlFatory.openSession().selectOne("Board.selectBoardOne", no);
	}

	@Override
	public int updateBoard(BoardVO obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(BoardVO obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countBoard() {
		return sqlFatory.openSession().selectOne("Board.count");
	}

	@Override
	public int updateHit(int no) {
		return sqlFatory.openSession().update("Board.updateHit", no);
	}

	@Override
	public int insertBatch(List<BoardVO> list) {
		return sqlFatory.openSession().insert("Board.insertItemBatch", list);
	}

	@Override
	public BoardVO selectBoardImg(int no) {
		return sqlFatory.openSession().selectOne("Board.selectBoardImg", no);
	}


}
