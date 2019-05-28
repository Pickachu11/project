package com.spring.client.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.client.board.service.BoardService;
import com.spring.client.board.vo.BoardVO;
import com.spring.common.vo.PageDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService boardService;
	
	/******************************
	 * 글목록 구현하기(페이징 처리)
	 ******************************/
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardList 호출 성공");
		log.info("keyword : " + bvo.getKeyword());
		log.info("search : " + bvo.getSearch());
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		//전체 레코드 수 구현
		int total = boardService.boardListCnt(bvo);
		model.addAttribute("pageMaker", new PageDTO(bvo, total));
		
		return "board/boardList";
	}
	
	/************************
	 * 글쓰기 폼 출력하기
	 ************************/
	@RequestMapping(value="/writeForm")
	public String writeForm(@ModelAttribute("data") BoardVO bvo) {
		log.info("writeForm 호출 성공");
		
		return "board/writeForm";
	}
	
	/************************
	 * 새 게시물 등록
	 ************************/
	@RequestMapping(value="/boardInsert", method = RequestMethod.POST)
	public String boardInsert(BoardVO bvo, Model model) {
		log.info("boardInsert 호출 성공");
		
		int result = 0;
		String url = "";
		
		result = boardService.boardInsert(bvo);
		if(result == 1) {
			url= "/board/boardList";
		}/* else {
			model.addAttribute("code", 1);
			url = "/board/writeForm";
		}*/
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		return "redirect:" + url;
	}
	
	/************************
	 * 글 상세보기 구현
	 ************************/
	@RequestMapping(value="/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardDetail 호출 성공");
		log.info("bvo = " + bvo);
		//log.info("b_num = " + bvo.getB_num());
		
		BoardVO detail = boardService.boardDetail(bvo);
		model.addAttribute("detail", detail);
		
		return "board/boardDetail";
	}
	
	
	/************************
	 * 비밀번호 확인
	 ************************/
	@ResponseBody
	@RequestMapping(value="/pwdConfirm", method=RequestMethod.POST, produces= "text/plain; charset=UTF-8")
	public String pwdConfirm(BoardVO bvo) {
		log.info("pwdConfirm 호출 성공");
		String value  = "";
		
		//입력 성공하면 1, 실패하면 0 반환
		int result = boardService.pwdConfirm(bvo);
		if(result==1) {
			value = "성공";
		}else {
			value = "실패";
		}
		log.info("result = " + result);
		
		return value;
	}
	
	/************************
	 * 글 수정
	 ************************/
	@RequestMapping(value="/updateForm")
	public String updateForm(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("updateForm 호출성공");
		log.info("b_num = " + bvo.getB_num());
		
		BoardVO updateData = boardService.updateForm(bvo);
		
		model.addAttribute("updateData", updateData);
		
		return "board/updateForm";
	}
	
	/************************
	 * 게시물 수정
	 ************************/
	@RequestMapping(value="/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo, RedirectAttributes ras) {
		log.info("boardUpdate 호출 성공");
		
		int result = 0;
		String url = "";
		
		result = boardService.boardUpdate(bvo);
		ras.addFlashAttribute("data", bvo); //redirect할 때 파라미터를 돌려준다. 매핑된 컨트롤에서 vo를 받고있으므로 vo로 넘겨준다.
		//브라우저까지 전송되기는 하지만 URI상에 보이지않는 숨겨진 데이터의 형태로 전달된다.
		
		if(result == 1) {
			//url= "/board/boardDetail?b_num=" + bvo.getB_num();
			url= "/board/boardDetail";
		} else {
			//url= "/board/updateForm?b_num=" + bvo.getB_num();	
			url= "/board/updateForm";	
		}
		
		return "redirect:" + url; //redirect를 써서 매핑된 주소로 이동할 수 있다.
	}
	
	/************************
	 * 게시물 삭제
	 ************************/
	@RequestMapping(value="/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo) {
		log.info("boardDelete 호출 성공");
		
		int result = 0;
		String url = "";
		
		result = boardService.boardDelete(bvo.getB_num());
		
		if(result == 1) {
			url = "/board/boardList";
		}else {
			url= "/board/boardDetail?b_num=" + bvo.getB_num();
		}
		
		return "redirect:" + url;
	}
	
	/**
	 * 글 삭제 전 댓글 갯수 확인
	 * @param int
	 */
	@ResponseBody
	@RequestMapping(value="/replyCnt")
	public String replyCnt(@RequestParam("b_num") int b_num) {
		log.info("replyCnt 호출 성공");
		
		int result = 0;
		result = boardService.replyCnt(b_num);
		
		return String.valueOf(result);
	}
}
