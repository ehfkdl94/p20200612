package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.ItemVO;
import com.example.vo.MemberVO;

@Service
@Transactional
public class MemberDAO {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private SqlSessionFactory sqlFatory = null;
	
	public int insertMember(MemberVO obj) {
		return sqlFatory.openSession().insert("Member.join", obj);
	}
	
	public List<MemberVO> selectMemberList() {
		return sqlFatory.openSession().selectList("Member.memberlist");
	}
	
	public MemberVO selectMemberLogin(MemberVO obj) {
		return sqlFatory.openSession().selectOne("Member.login", obj);
	}

	public int countBoard() {
		return sqlFatory.openSession().selectOne("Member.count");
	}
	
	public List<MemberVO> selectMember(HashMap<String, Object> map){
		return sqlFatory.openSession().selectList("Member.memberlist2", map);
		
	}
	
	public int deleteMember(String[] no) {
		return sqlFatory.openSession().delete("Member.deleteMemberBatch", no);
	}
}
