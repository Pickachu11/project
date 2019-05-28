package com.spring.client.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.board.dao.BoardDao;
import com.spring.client.reply.dao.ReplyDao;
import com.spring.client.reply.vo.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_=@Autowired)
	private ReplyDao replyDao;
	
	@Setter(onMethod_=@Autowired)
	private BoardDao boardDao;

	@Override
	public List<ReplyVO> replyList(Integer b_num) {
		List<ReplyVO> list = null;
		list = replyDao.replyList(b_num);
		return list;
	}

	/*@Override
	public int replyInsert(ReplyVO rvo) {
		int result = 0;
		result = replyDao.replyInsert(rvo);
		return result;
	}*/
	
	@Transactional
	@Override
	public int replyInsert(ReplyVO rvo) {
		int result = 0;
		
		result = replyDao.replyInsert(rvo);
		boardDao.replyCntUpdate(rvo.getB_num(), 1);
		
		return result;
	}

	@Override
	public int pwdConfirm(ReplyVO rvo) {
		int result = 0;
		result = replyDao.pwdConfirm(rvo);
		return result;
	}

	@Override
	public int replyDelete(int r_num, int b_num) {
		int result = 0;
		result = replyDao.replyDelete(r_num);
		boardDao.replyCntUpdate(b_num, -1);
		return result;
	}
	
	//수정 전 레코드 조회
	@Override
	public ReplyVO replySelect(int r_num) {
		ReplyVO rvo = null;
		rvo = replyDao.replySelect(r_num);
		return rvo;
	}

	@Override
	public int replyUpdate(ReplyVO rvo) {
		int result = 0;
		result = replyDao.replyUpdate(rvo);
		return result;
	}
}
