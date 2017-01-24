package com.zcbspay.platform.business.merch.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.business.commons.utils.StringUtil;
import com.zcbspay.platform.business.exception.BusinessMerchException;
import com.zcbspay.platform.business.merch.service.DataCheckFailedException;
import com.zcbspay.platform.business.merch.service.MerchService;
import com.zcbspay.platform.member.individual.bean.MemberBean;
import com.zcbspay.platform.member.individual.bean.PoMemberBean;
import com.zcbspay.platform.member.individual.bean.enums.MemberType;
import com.zcbspay.platform.member.individual.service.MemberOperationService;
import com.zcbspay.platform.member.individual.service.MemberService;
import com.zcbspay.platform.member.merchant.bean.MerchMK;
import com.zcbspay.platform.member.merchant.service.MerchMKService;

@Service
public class MerchServiceImpl implements MerchService {
	private final static Logger log = LoggerFactory.getLogger(MerchServiceImpl.class);
	@Autowired
    private MerchMKService merchMKService;
	@Autowired
	private  MemberService memberService;
	@Autowired
	private MemberOperationService memberOperationService;
	
	/**
	 * 更新商户公钥
	 * @param memberId
	 * @param pub_key
	 * @return
	 */
	@Override
	public boolean updateMerchPubKey(String memberId, String pub_key) throws BusinessMerchException {
		if(StringUtil.isEmpty(memberId)|| StringUtil.isEmpty(pub_key)){
			throw new BusinessMerchException("BT0000");//参数不能为空
		}
		MerchMK merchMK = merchMKService.get(memberId);
		if(merchMK==null){
			//商户秘钥不存在异常
			throw new BusinessMerchException("BT0001");//"商户秘钥不存在异常"
		}
		merchMK.setMemberPubKey(pub_key);
		try {
			merchMKService.updateMerchMK(merchMK);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessMerchException("BT0002");//修改商户公钥异常
		}
		return true;
	}

	/**
	 * 重置商户支付密码
	 * @param memberId
	 * @param pwd
	 * @return
	 * @throws DataCheckFailedException
	 */
	@Override
	public boolean resetPayPwd(String memberId, String payPwd) throws BusinessMerchException {
		if(StringUtil.isEmpty(memberId)|| StringUtil.isEmpty(payPwd)){
			throw new BusinessMerchException("BT0000");//参数不能为空
		}
		PoMemberBean pm = memberService.getMbmberByMemberId(memberId, MemberType.ENTERPRISE);
		if(pm==null){
			throw new BusinessMerchException("BT0003");//查询不到会员信息
		}
		MemberBean member = new MemberBean();
		member.setLoginName(pm.getLoginName());
		member.setInstiId(pm.getInstiId());
		member.setPhone(pm.getPhone());
		try {
			return memberOperationService.resetPayPwd(MemberType.ENTERPRISE, member, payPwd, false);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessMerchException("BT0004");//重置支付密码异常
		}
	}

	

}
