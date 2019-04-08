package spittr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spittr.entity.Spitter;

public interface SpitterRepository extends JpaRepository<Spitter, Long>, SpitterSweeper{
	//自动解析方法名
	Spitter findByUsername(String username);
	
	List<Spitter> readByFirstnameIgnoringCaseOrLastnameIgnoringCase(String first, String last);
	//AllIgnoresCase 无法识别
	List<Spitter> readByFirstnameOrLastnameAllIgnoringCase(String first, String last);
	
	//自定义查询
/*	@Query("select s from Spitter s where s.email like '%gmail.com'")
	List<Spitter> findAllGmailSpitters();*/
	
	
}
