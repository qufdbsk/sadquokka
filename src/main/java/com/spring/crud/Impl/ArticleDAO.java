package com.spring.crud.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.crud.ArticleVO;

//구현클래스는 저장소에 연계해줘야한다. repository 를 이용하여,
@Repository
public class ArticleDAO {
	
	//마이바티스 템플릿 활용
	private SqlSessionTemplate mybatis;
	
	//의존주입
	@Autowired
	public ArticleDAO(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	public void insert(ArticleVO vo) {
		mybatis.insert("ArticleDAO.insert", vo);
		
	}
	
	public ArticleVO selectById(ArticleVO vo) {
		return (ArticleVO)mybatis.selectOne("ArticleDAO.selectById",vo);
	}
	
	public void update(ArticleVO vo) {
		mybatis.update("ArticleDAO.update",vo);
	}
	
	public void delete(ArticleVO vo) {
		mybatis.delete("ArticleDAO.delete",vo);
	}

}
