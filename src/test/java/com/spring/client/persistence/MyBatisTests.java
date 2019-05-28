package com.spring.client.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.client.board.dao.BoardDao;
import com.spring.client.board.vo.BoardVO;
import com.spring.client.reply.dao.ReplyDao;
import com.spring.client.reply.vo.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MyBatisTests {
	@Setter(onMethod_= @Autowired)
	private BoardDao boardDao;
	
	@Setter(onMethod_= @Autowired)
	private ReplyDao replyDao;
	
	/*@Test
	public void testBoardList() {
		log.info(boardDao.getClass().getName());
		log.info("boardList() 메서드 실행");

		log.info("--------------------------------------------");
		BoardVO bvo = new BoardVO();
		bvo.setPageNum(1);
		bvo.setAmount(7);
		
		bvo.setSearch("b_title");
		bvo.setKeyword("힘");
		
		log.info(boardDao.boardList(bvo));
	}*/
	
	@Test
	public void testBoardListCnt() {
		log.info("boardList() 메서드 실행");

		log.info("--------------------------------------------");
		BoardVO bvo = new BoardVO();
		bvo.setSearch("b_title");
		bvo.setKeyword("나도");
		log.info("레코드 수 : " + boardDao.boardListCnt(bvo));
	}
	
	/*@Test
	public void testBoardInsert1() {
		BoardVO bvo = new BoardVO();
		bvo.setB_name("김철수");
		bvo.setB_title("힘들때 힘이 되는 명언2");
		bvo.setB_content("소심하고 망설이는 자에게는 모든 것이 불가능하다. 왜냐하면 모든것이 불가능하게 보이기 때문이다.");
		bvo.setB_pwd("1234");
		
		log.info("BoardVO : " + bvo);
		log.info("------------------------");
		log.info("반환값: " + boardDao.boardInsert(bvo));
	}*/
	
	/*@Test
	public void testBoardInsert2() {
		BoardVO bvo = new BoardVO();
		bvo.setB_name("이희진");
		bvo.setB_title("힘들때 힘이 되는 명언3");
		bvo.setB_content("세상에 완벽한 준비란 없습니다. 삶은 어차피 모험이고 그 모험을 통해 내 영혼이 성숙해 지는 학교입니다.");
		bvo.setB_pwd("1234");
		
		log.info("BoardVO : " + bvo);
		log.info("------------------------");
		log.info("반환값: " + boardDao.boardInsert(bvo));
	}*/
	
	/*@Test
	public void testBoardDetail() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(2);
		log.info("BoardVO : " + bvo);
		log.info("-------------------------");
		log.info("반환값:" + boardDao.boardDetail(bvo));
	}*/
	
	
	/*@Test
	public void testPwdConfirm() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(2);
		bvo.setB_pwd("1235");
		log.info("BoardVO : " + bvo);
		log.info("-------------------------");
		log.info("반환값(0 or 1):" + boardDao.pwdConfirm(bvo));	//비밀번호 일치하면 1, 아니면 0 반환
	}*/
	
	/*@Test
	public void testBoardUpdate() {
		BoardVO bvo = new BoardVO();
		bvo.setB_num(26);
		bvo.setB_title("제목수정2");
		bvo.setB_content("내용수정2");
		//bvo.setB_pwd("12345");
		log.info("BoardVO : " + bvo);
		log.info("-------------------------");
		log.info("반환값:" + boardDao.boardUpdate(bvo));
	}*/
	
	/*@Test
	public void testBoardDelete() {
		int b_num = 26;
		log.info("반환값:" + boardDao.boardDelete(b_num));
	}*/
	
	
	/*@Test
	public void replyList() {
		log.info(replyDao.getClass().getName());
		log.info("replyList() 메서드 실행");
		log.info(replyDao.replyList(42));
		log.info("--------------------------------------------");
	}*/
	
	/*@Test
	public void testReplyInsert() {
		ReplyVO rvo = new ReplyVO();
		rvo.setB_num(42);
		rvo.setR_name("혜인");
		rvo.setR_content("난 신발 샀지롱~");
		rvo.setR_pwd("123");
		
		log.info("replyInsert() 메서드 실행");
		log.info("ReplyVO: " + rvo);
		log.info("반환값:" + replyDao.replyInsert(rvo));
	}*/
	
	/*@Test 
	public void testPwdConfirm() {
		ReplyVO rvo = new ReplyVO();
		rvo.setR_num(2);
		rvo.setR_pwd("1234");
		
		log.info("pwdConfirm() 메서드 실행");
		log.info("반환값:" + replyDao.pwdConfirm(rvo));
	}*/
	
	/*@Test 
	public void testReplyDelete() {
		log.info("댓글 삭제 반환값: " + replyDao.replyDelete(2));
	}*/
	
}