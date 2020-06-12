package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.ItemVO;

@Service
@Transactional
public class ItemDAOImpl implements ItemDAO{
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private SqlSessionFactory sqlFatory = null;
	
	@Override
	public int insertItem(ItemVO obj) {
		return 0;//글쓰기
		
	}
	
	@Override
	public List<ItemVO> selectItem(HashMap<String, Object> map){
		return sqlFatory.openSession().selectList("Item.itemlist", map);
		
	}
	
	@Override
	public ItemVO selectItemOne(int no) {
		return sqlFatory.openSession().selectOne("Item.selectItemOne", no);
		
	}
	
	@Override
	public int updateItem(ItemVO obj) {
		return 0;
	}
	
	
	@Override
	public int countBoard() {
		return sqlFatory.openSession().selectOne("Item.count");
	}

	@Override
	public List<ItemVO> selectItemSearch(String txt) {
		return sqlFatory.openSession().selectList("Item.selectItemSearch", txt);
	}

	@Override
	public int insertItemBatch(List<ItemVO> list) {
		return sqlFatory.openSession().insert("Item.insertItemBatch", list);
	}

	@Override
	public int deleteItem(int[] no) {
		return sqlFatory.openSession().delete("Item.deleteItemBatch", no);
	}
	
	@Override
	public List<ItemVO> selectItemWhere(int[] itemno) {
		return sqlFatory.openSession().selectList("Item.selectItemWhere", itemno);
	}

	@Override
	public int updateItemBatch(List<ItemVO> list) {
		return sqlFatory.openSession().update("Item.updateItemBatch", list);
	}

	

}
