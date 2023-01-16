package com.spring.crud;

public interface ArticleService { //제공하는 서비스 이것을 구현하는 게 IMPL
	ArticleVO selectById(ArticleVO vo);
	
	void insert(ArticleVO vo);
	void update(ArticleVO vo);
	void delete(ArticleVO vo);
	
	
	
}
