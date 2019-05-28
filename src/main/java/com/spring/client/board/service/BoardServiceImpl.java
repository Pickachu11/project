package com.spring.client.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.client.board.dao.BoardDao;
import com.spring.client.board.vo.BoardVO;
import com.spring.client.reply.dao.ReplyDao;
import com.spring.client.reply.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_=@Autowired)
	private BoardDao boardDao;
	
	@Setter(onMethod_=@Autowired)
	private ReplyDao replyDao;
	
	//글목록
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		log.info("boardList...........");
		
		List<BoardVO> myList = null;
		myList = boardDao.boardList(bvo);
		
		return myList;
	}
	
	@Override
	public int boardListCnt(BoardVO bvo) {
		return boardDao.boardListCnt(bvo);
	}

	//글입력
	@Override
	public int boardInsert(BoardVO bvo) {
		log.info("boardInsert...........");
		int result = 0;
		
		/*예외를 발생시킬 코드*/
		bvo.setB_num(0);
		if(bvo.getB_num() == 0) {
			throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
		}
		
		result = boardDao.boardInsert(bvo);
		
		return result;
	}
	
	//글 상세 구현
	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		BoardVO detail = null;
		detail = boardDao.boardDetail(bvo);
		if(detail!=null) {
			detail.setB_content(detail.getB_content().toString().replaceAll("\n", "<br>"));
		}
		return detail;
	}
	
	//비밀번호 확인
	@Override
	public int pwdConfirm(BoardVO bvo) {
		int result = 0;
		result = boardDao.pwdConfirm(bvo);
		return result;
	}
	
	//글 수정폼
	@Override
	public BoardVO updateForm(BoardVO bvo) {
		BoardVO detail = null;
		detail = boardDao.boardDetail(bvo);
		return detail;
	}

	//글 수정 작업
	@Override
	public int boardUpdate(BoardVO bvo) {
		int result = 0;
		result = boardDao.boardUpdate(bvo);
		return result;
	}
	
	//글 삭제
	@Override
	public int boardDelete(int b_num) {
		int result = 0;
		result = boardDao.boardDelete(b_num);
		return result;
	}

	@Override
	public int replyCnt(int b_num) {
		int result = 0;
		
		/*List<ReplyVO> list = replyDao.replyList(b_num);
		
		if(!list.isEmpty()) {
			result = list.size();
		}else {
			result = 0;
		}*/
		
		result = replyDao.replyCnt(b_num);
		
		return result;
	}

	
	
	

}
