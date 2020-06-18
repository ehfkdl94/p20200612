package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.entity.Student;

//CrudRepository<엔티티, id타입(long)>
//repository가 DAO역학을 한다. 상속으로 인해서 CRUD가 다 들어있음
public interface StudentRepository extends CrudRepository<Student, Long>{
	//SELECT * FROM STUDENT WHERE KOR > #{kor}?
	List<Student> findByKorGreaterThan(int kor);
	
	//SELECT * FROM STUDENT WHERE ST_ID = #{id}
	Student findById(String id);
	
	@Query(value="SELECT * FROM STUDENT WHERE ST_KOR >= :kor", nativeQuery=true)
	List<Student> selectStudentQuery(@Param("kor")int kor);
}
