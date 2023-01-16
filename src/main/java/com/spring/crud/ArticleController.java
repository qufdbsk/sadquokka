package com.spring.crud;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러는 뷰를 리턴시켜준다.
@Controller
public class ArticleController {
	
	//의존주입
	@Autowired
	private ArticleService articleService;
	
	//아래 모든 메서드에 request.setAttribute("article",articleService.selectById(vo))를
	//해주는 것과 같은 역할을 하는 getArticle() 메서드 정의
	
	@ModelAttribute("article")
	public ArticleVO getArticle() {
		ArticleVO vo = new ArticleVO();
		return articleService.selectById(vo);
	}
	
	//1.게시글 등록
	@RequestMapping("/article/write.do")
	public String insert(HttpServletRequest request, HttpServletResponse response, ArticleVO vo) {
			return process(request, response , vo, "insert");
	}
	
	
	//2.최근 게시글 조회
	@RequestMapping("/article/read.do")
	public String selectById(ArticleVO vo) {
		return "/WEB-INF/views/readArticle.jsp";
	}
	
	//3.최근 게시글 수정
	@RequestMapping("/article/modify.do")
	public String update(HttpServletRequest request, HttpServletResponse response, ArticleVO vo) {
		return process(request, response, vo, "update");
	}
	
	//4.최근 게시글 삭제
	@RequestMapping("/article/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, ArticleVO vo) {
		return process(request,response,vo,"delete");
	}

	//GET, POST, 그밖의 요청에 대한 분기 처리를 해주는 process() 메서드 정의
	private String process(HttpServletRequest request, HttpServletResponse response, ArticleVO vo, String query) {
	
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return viewResolverForm(query);
			
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			executeQuery(query,vo);
			return viewResolverSuccess(query);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return null;
	}
	
	//성공화면경로를 만들어주는 viewResolverSuccess() 메서드를 정의함.
	private String viewResolverSuccess(String viewName) {
		return "/WEB-INF/views/"+viewName+"Success.jsp";
	}
	
	//입력한 쿼리문에 따라 메서드별 선택 실행하는 executeQuery()메서드를 정의함.
	private void executeQuery(String query, ArticleVO vo) {
		if (query == "insert") {
			articleService.insert(vo);
			
		}else if(query == "update") {
			articleService.update(vo);
		}else if (query == "delete") {
			articleService.delete(vo);
		}
	}

	//폼 화면 경로를 만들어주는 메서드
	private String viewResolverForm(String viewName) {
		
		return "/WEB-INF/views/"+viewName+"Form.jsp";
	}
	
	
	

}
