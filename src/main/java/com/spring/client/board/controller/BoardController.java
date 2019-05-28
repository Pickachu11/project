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
	 * �۸�� �����ϱ�(����¡ ó��)
	 ******************************/
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardList ȣ�� ����");
		log.info("keyword : " + bvo.getKeyword());
		log.info("search : " + bvo.getSearch());
		
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		//��ü ���ڵ� �� ����
		int total = boardService.boardListCnt(bvo);
		model.addAttribute("pageMaker", new PageDTO(bvo, total));
		
		return "board/boardList";
	}
	
	/************************
	 * �۾��� �� ����ϱ�
	 ************************/
	@RequestMapping(value="/writeForm")
	public String writeForm(@ModelAttribute("data") BoardVO bvo) {
		log.info("writeForm ȣ�� ����");
		
		return "board/writeForm";
	}
	
	/************************
	 * �� �Խù� ���
	 ************************/
	@RequestMapping(value="/boardInsert", method = RequestMethod.POST)
	public String boardInsert(BoardVO bvo, Model model) {
		log.info("boardInsert ȣ�� ����");
		
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
	 * �� �󼼺��� ����
	 ************************/
	@RequestMapping(value="/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardDetail ȣ�� ����");
		log.info("bvo = " + bvo);
		//log.info("b_num = " + bvo.getB_num());
		
		BoardVO detail = boardService.boardDetail(bvo);
		model.addAttribute("detail", detail);
		
		return "board/boardDetail";
	}
	
	
	/************************
	 * ��й�ȣ Ȯ��
	 ************************/
	@ResponseBody
	@RequestMapping(value="/pwdConfirm", method=RequestMethod.POST, produces= "text/plain; charset=UTF-8")
	public String pwdConfirm(BoardVO bvo) {
		log.info("pwdConfirm ȣ�� ����");
		String value  = "";
		
		//�Է� �����ϸ� 1, �����ϸ� 0 ��ȯ
		int result = boardService.pwdConfirm(bvo);
		if(result==1) {
			value = "����";
		}else {
			value = "����";
		}
		log.info("result = " + result);
		
		return value;
	}
	
	/************************
	 * �� ����
	 ************************/
	@RequestMapping(value="/updateForm")
	public String updateForm(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("updateForm ȣ�⼺��");
		log.info("b_num = " + bvo.getB_num());
		
		BoardVO updateData = boardService.updateForm(bvo);
		
		model.addAttribute("updateData", updateData);
		
		return "board/updateForm";
	}
	
	/************************
	 * �Խù� ����
	 ************************/
	@RequestMapping(value="/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo, RedirectAttributes ras) {
		log.info("boardUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardService.boardUpdate(bvo);
		ras.addFlashAttribute("data", bvo); //redirect�� �� �Ķ���͸� �����ش�. ���ε� ��Ʈ�ѿ��� vo�� �ް������Ƿ� vo�� �Ѱ��ش�.
		//���������� ���۵Ǳ�� ������ URI�� �������ʴ� ������ �������� ���·� ���޵ȴ�.
		
		if(result == 1) {
			//url= "/board/boardDetail?b_num=" + bvo.getB_num();
			url= "/board/boardDetail";
		} else {
			//url= "/board/updateForm?b_num=" + bvo.getB_num();	
			url= "/board/updateForm";	
		}
		
		return "redirect:" + url; //redirect�� �Ἥ ���ε� �ּҷ� �̵��� �� �ִ�.
	}
	
	/************************
	 * �Խù� ����
	 ************************/
	@RequestMapping(value="/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo) {
		log.info("boardDelete ȣ�� ����");
		
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
	 * �� ���� �� ��� ���� Ȯ��
	 * @param int
	 */
	@ResponseBody
	@RequestMapping(value="/replyCnt")
	public String replyCnt(@RequestParam("b_num") int b_num) {
		log.info("replyCnt ȣ�� ����");
		
		int result = 0;
		result = boardService.replyCnt(b_num);
		
		return String.valueOf(result);
	}
}
