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
	
	/*�Ķ���͸� ���ε��Ҷ� �ڵ����� ȣ��Ǵ� @InitBinder�� �̿��ؼ� */ 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MultipartFile.class, "file", new StringTrimmerEditor(true));
	}
	
	@GetMapping("/galleryList")
	public String galleryList() {
		log.info("galleryList ȣ�� ����");
		return "gallery/galleryList";
	}
	
	/**
	 * ������ ��� �����ϱ�
	 * @param GalleryVO
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/galleryData", produces="text/plane; charset=UTF-8")
	public String galleryData(GalleryVO gvo) {
		log.info("galleryData ȣ�� ����");
		String listData = galleryService.galleryList(gvo);
		
		return listData;
	}
	
	/**
	 * �� �󼼺��� ����
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/galleryDetail", produces="text/plane; charset=UTF-8")
	public String galleryDetail(@ModelAttribute GalleryVO gvo) {
		log.info("galleryDetail ȣ�� ����");
		
		String detail = galleryService.galleryDetail(gvo);
		return detail;
	}
	
	/**
	 * �۾��� �����ϱ�
	 * @param GalleryVO
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/galleryInsert", method=RequestMethod.POST, produces="text/plane; charset=UTF-8")
	public String galleryInsert(@ModelAttribute GalleryVO gvo) {
		log.info("galleryInsert ȣ�� ����");
		
		log.info("file name : " + gvo.getFile().getOriginalFilename());
		String value = "";
		int result = 0;
		
		result = galleryService.galleryInsert(gvo);
		if(result==1) {
			value = "����";
		} else {
			value = "����";
		}
		return value;
	}
	
	/**
	 * ��й�ȣ ��ġ Ȯ��
	 * @param GalleryVO
	 * ���� : @ResponseBody�� ���޵� �並 ���ؼ� ����ϴ� ���� �ƴ϶� Http Response Body�� ���� ����ϴ� ���
	 */
	@ResponseBody
	//@RequestMapping(value="/pwdConfirm", method=RequestMethod.POST, produces="text/plane; charset=UTF-8")
	@PostMapping(value="/pwdConfirm", produces=MediaType.TEXT_PLAIN_VALUE)
	public String pwdConfirm(@ModelAttribute GalleryVO gvo) {
		log.info("pwdConfirm ȣ�� ����");
		
		int result = 0;
		result = galleryService.pwdConfirm(gvo);
		log.info("result : " + result);
		
		return String.valueOf(result);
	}
	
	
	/**
	 * ������ ���� ���� 
	 * ����+÷������ ���� / ���븸 ���� / ��й�ȣ ���� ����
	 * @param gvo
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/galleryUpdate", produces="text/plain; charset=UTF-8")
	public String galleryUpdate(@ModelAttribute GalleryVO gvo) {
		log.info("galleryUpdate ȣ�� ����");
		
		String value = "";
		int result = 0;
		
		result = galleryService.galleryUpdate(gvo);
		if(result==1) {
			value = "����";
		}else {
			value = "����";
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
			value = "����";
		} else {
			value = "����";
		}
		
		return value;
	}
}
