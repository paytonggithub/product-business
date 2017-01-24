package com.zcbspay.platform.business.order.service;

import com.zcbspay.platform.business.exception.BusinessOrderException;
import com.zcbspay.platform.business.order.bean.InstPayOrderBean;
import com.zcbspay.platform.business.order.bean.OrderBean;
import com.zcbspay.platform.business.order.bean.ResultBean;
/***
 * 订单相关
 * @author zhanglh
 *
 */
public interface OrderService {
	/***
	 * 创建消费订单
	 * @param order
	 * @return
	 */
	public ResultBean createConsumeOrder(OrderBean order) throws BusinessOrderException;
	
	/***
	 * 创建退款订单
	 * @param order
	 * @return
	 */
//	public ResultBean createRefundOrder(RefundOrderBean order) throws BusinessOrderException;
	/***
	 * 创建实时代付订单
	 * @param order
	 * @return
	 */
	public ResultBean createInsteadPayOrder(InstPayOrderBean order)throws BusinessOrderException;
    /****
     * 创建充值订单
     * @param order
     * @return
     * @throws BusinessOrderException
     */
//	public ResultBean recharge(OrderBean order)throws BusinessOrderException;
	/***
	 * 资金账户余额提现-有支付密码
	 * @param json
	 * @param payPwd
	 * @return
	 * @throws BusinessOrderException
	 */
//	public ResultBean withdraw(WapWithdrawBean withdrawBean, String payPwd) throws BusinessOrderException;
	
	 /**
     * 资金账户余额提现-无支付密码
     * @param withdrawBean
     * @return
     * @throws CommonException
     */
//    public ResultBean withdraw(WapWithdrawBean withdrawBean) throws BusinessOrderException;
}
