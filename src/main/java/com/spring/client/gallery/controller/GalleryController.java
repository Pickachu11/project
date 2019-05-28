package com.spring.client.gallery.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.client.gallery.service.GalleryService;
import com.spring.client.gallery.vo.GalleryVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/gallery/*")
@Log4j
@AllArgsConstructor
public class GalleryController {
	private GalleryService galleryService;
	
	/*파라미터를 바인딩할때 자동으로 호출되는 @InitBinder를 이용해서 */ 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MultipartFile.class, "file", new StringTrimmerEditor(true));
	}
	
	@GetMapping("/galleryList")
	public String galleryList() {
		log.info("galleryList 호출 성공");
		return "gallery/galleryList";
	}
	
	/**
	 * 갤러리 목록 구현하기
	 * @param GalleryVO
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/galleryData", produces="text/plane; charset=UTF-8")
	public String galleryData(GalleryVO gvo) {
		log.info("galleryData 호출 성공");
		String listData = galleryService.galleryList(gvo);
		
		return listData;
	}
	
	/**
	 * 글 상세보기 구현
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/galleryDetail", produces="text/plane; charset=UTF-8")
	public String galleryDetail(@ModelAttribute GalleryVO gvo) {
		log.info("galleryDetail 호출 성공");
		
		String detail = galleryService.galleryDetail(gvo);
		return detail;
	}
	
	/**
	 * 글쓰기 구현하기
	 * @param GalleryVO
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/galleryInsert", method=RequestMethod.POST, produces="text/plane; charset=UTF-8")
	public String galleryInsert(@ModelAttribute GalleryVO gvo) {
		log.info("galleryInsert 호출 성공");
		
		log.info("file name : " + gvo.getFile().getOriginalFilename());
		String value = "";
		int result = 0;
		
		result = galleryService.galleryInsert(gvo);
		if(result==1) {
			value = "성공";
		} else {
			value = "실패";
		}
		return value;
	}
	
	/**
	 * 비밀번호 일치 확인
	 * @param GalleryVO
	 * 참고 : @ResponseBody는 전달된 뷰를 통해서 출력하는 것이 아니라 Http Response Body에 직접 출력하는 방식
	 */
	@ResponseBody
	//@RequestMapping(value="/pwdConfirm", method=RequestMethod.POST, produces="text/plane; charset=UTF-8")
	@PostMapping(value="/pwdConfirm", produces=MediaType.TEXT_PLAIN_VALUE)
	public String pwdConfirm(@ModelAttribute GalleryVO gvo) {
		log.info("pwdConfirm 호출 성공");
		
		int result = 0;
		result = galleryService.pwdConfirm(gvo);
		log.info("result : " + result);
		
		return String.valueOf(result);
	}
	
	
	/**
	 * 갤러리 수정 구현 
	 * 내용+첨부파일 수정 / 내용만 수정 / 비밀번호 수정 제어
	 * @param gvo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/galleryUpdate", produces="text/plain; charset=UTF-8")
	public String galleryUpdate(@ModelAttribute GalleryVO gvo) {
		log.info("galleryUpdate 호출 성공");
		
		String value = "";
		int result = 0;
		
		result = galleryService.galleryUpdate(gvo);
		if(result==1) {
			value = "성공";
		}else {
			value = "실패";
		}
		
		return value;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/galleryDelete", method=RequestMethod.POST, produces="text/plane; charset=UTF-8")
	public String galleryDelete(@ModelAttribute GalleryVO gvo) {
		int result = 0;
		String value = "";
		
		result = galleryService.galleryDelete(gvo);
		if(result==1) {
			value = "성공";
		} else {
			value = "실패";
		}
		
		return value;
	}
}
