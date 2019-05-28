package com.spring.client.board.vo;

import com.spring.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BoardVO extends CommonVO{
	private int    b_num     =0; 
	private String b_name    =""; 
	private String b_title   ="";
	private String b_content =""; 
	private String b_date    ="";
	private String b_pwd     ="";
	private String r_cnt     ="";
}
