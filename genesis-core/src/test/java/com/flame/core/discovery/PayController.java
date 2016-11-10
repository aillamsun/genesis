//package com.flame.core.discovery;
//
//import com.alibaba.fastjson.JSON;
//import com.chinawiserv.credit.common.annotation.Auth;
//import com.chinawiserv.credit.common.constants.Constants;
//import com.chinawiserv.credit.common.enums.Response;
//import com.chinawiserv.credit.common.model.AjaxRepopnse;
//import com.chinawiserv.credit.common.utils.MessageUtils;
//import com.chinawiserv.credit.common.utils.StringUtils;
//import com.chinawiserv.credit.common.utils.time.DateUtils;
//import com.chinawiserv.credit.common.web.controller.BaseController;
//import com.chinawiserv.credit.model.PayOrder;
//import com.chinawiserv.credit.model.User;
//import com.chinawiserv.credit.pay.alipay.AlipayPayResult;
//import com.chinawiserv.credit.pay.alipay.common.AlipayNotify;
//import com.chinawiserv.credit.pay.common.PayFactory;
//import com.chinawiserv.credit.pay.common.PayParam;
//import com.chinawiserv.credit.pay.common.PayUtils;
//import com.chinawiserv.credit.pay.wechat.SignUtil;
//import com.chinawiserv.credit.pay.wechat.WechatConfig;
//import com.chinawiserv.credit.pay.wechat.WechatPayResult;
//import com.chinawiserv.credit.service.PayOrderService;
//import com.chinawiserv.credit.service.UserService;
//import com.chinawiserv.credit.utils.Arith;
//import com.chinawiserv.credit.utils.CodeProduceUtil;
//import com.chinawiserv.credit.utils.OrderUtil;
//import com.github.pagehelper.PageInfo;
//import com.google.common.collect.Lists;
//import net.sf.json.JSONObject;
//import org.apache.log4j.Logger;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import tk.mybatis.mapper.entity.Example;
//
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 支付相关
// * <p>
// * Created by sungang on 2016/9/3.
// */
//@Controller
//@RequestMapping("pay")
//public class PayController extends BaseController {
//
//
//    Logger logger = Logger.getLogger(PayController.class);
//
//    @Autowired
//    PayOrderService payOrderService;
//
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 阿里支付
//     *
//     * @param amount
//     * @return
//     */
//    @RequestMapping(value = "/alipay", method = RequestMethod.POST)
//    @Auth(verifyLogin = true)
//    public String alipay(HttpServletRequest request, String amount, Model model) {
//        try {
//            User userSess = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
//            int user_id = userSess.getId();
//            Double d_amount = Double.valueOf(amount);
//            String order_no = OrderUtil.getInstance().makeGiftNo();
//            PayParam payParam = new PayParam(PayParam.ALIPAY_PC_WEB, d_amount, order_no, "征信平台商户支付");
//            AlipayPayResult alipayPayResult = (AlipayPayResult) PayFactory.getPayHandle(PayFactory.tradePlat.ALIPAY).pay(payParam);
//            model.addAttribute("returnInfo", alipayPayResult.getReturnInfo());
//            String create_time = DateUtils.getCurrentTime(DateUtils.DEFAULT_FORMAT);
//            /**
//             * 生成支付订单
//             */
//            PayOrder payOrder = new PayOrder(user_id, order_no, PayOrder.PAY_TYPE.ALIPAY, d_amount, PayOrder.Status.UN_FINISHED, create_time);
//            payOrder.setPay_time(create_time);
//            payOrder.setOper_type("充值");
//            payOrder.setDetails("支付宝支付充值");
//            payOrderService.insertSelective(payOrder);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            model.addAttribute("returnInfo", MessageUtils.message("100100"));
//        }
//        return "pay/alipay";
//    }
//
//
//    /**
//     * 阿里支付 异步通知
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/aliPayReturn", method = RequestMethod.POST)
//    @ResponseBody
//    public String aliPayReturn(HttpServletRequest request) {
//        //获取支付宝POST过来反馈信息
//        Map<String, String> params = getParameterMap(request);
//        try {
//            System.out.println(JSON.toJSONString(params));
//            String out_trade_no = params.get("out_trade_no");
//
//            logger.info("out_trade_no：" +  out_trade_no);
//
//            PayOrder payOrder = new PayOrder();
//            payOrder.setOrder_no(out_trade_no);
//            PayOrder db_payOrder = payOrderService.selectOne(payOrder);
//            if (null == db_payOrder) {
//                return "fail";
//            } else {
//                if (db_payOrder.getStatus() == PayOrder.Status.SUCCESS) {
//                    return "success";
//                }
//
//                if (!AlipayNotify.verify(params)) {
//                    return "fail";
//                }
//                //判断交易状态
//                logger.info("支付宝异步返回交易状态：" +  params.get("trade_status"));
//                if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
//                    //更改订单状态
//                    int r = updateOrderStatus(out_trade_no);
//                    if (0 == r) {
//                        return "fail";
//                    }
//                    //更改用户余额
//                    int user_id = db_payOrder.getUser_id();
//                    String price = params.get("price");
//                    logger.info("支付宝交易金额：" +  price);
//                    int rr = updateBalance(user_id, Double.valueOf(price));
//                    if (0 == rr) {
//                        return "fail";
//                    }
//                    return "success";
//                } else {
//                    return "fail";
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return "fail";
//        }
//    }
//
//    /**
//     * 阿里支付 同步
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/aliPaySyncReturn", method = RequestMethod.GET)
//    public String aliPaySyncReturn(HttpServletRequest request) {
//        Map<String, String> params = getParameterMap(request);
////        System.out.println(JSON.toJSONString(params));
//        //判断是否支付成功
//        logger.info("支付宝同步返回交易状态：" +  params.get("trade_status"));
//        if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
//            //跳到余额页面
//            return "redirect:/user/balance";
//        } else {
//            return "user/userRechargeError";
//        }
//    }
//
//    /**
//     * 微信支付
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxRepopnse wechat(HttpServletRequest request, String amount) {
//        AjaxRepopnse ajaxRepopnse = new AjaxRepopnse();
//        JSONObject jo = new JSONObject();
//        try {
//            User userSess = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
//            int user_id = userSess.getId();
//            String order_no = OrderUtil.getInstance().makeGiftNo();
//            Double d_amount = Double.valueOf(amount);
//            PayParam payParam = new PayParam(PayParam.WX_NATIVE, d_amount, order_no, "征信平台交易");
//            WechatPayResult wechatPayResult = (WechatPayResult) PayFactory.getPayHandle(PayFactory.tradePlat.WECHAT).pay(payParam);
//            String code_url = wechatPayResult.getCodeUrl();
////            ajaxRepopnse.setData(code_url);
//            jo.put("code_url",code_url);
//            jo.put("order_no",order_no);
//            ajaxRepopnse.setData(jo.toString());
//            String create_time = DateUtils.getCurrentTime(DateUtils.DEFAULT_FORMAT);
//            /**
//             * 生成支付订单
//             */
//            PayOrder payOrder = new PayOrder(user_id, order_no, PayOrder.PAY_TYPE.WECHAT, d_amount, PayOrder.Status.UN_FINISHED, create_time);
//            payOrder.setPay_time(create_time);
//            payOrder.setOper_type("充值");
//            payOrder.setDetails("微信支付充值");
//            payOrderService.insert(payOrder);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            ajaxRepopnse.setCode(Response.KO);
//            ajaxRepopnse.setData(MessageUtils.message("10010"));
//        }
//        return ajaxRepopnse;
//    }
//
//    /**
//     * 生成二维码
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "/getQrcodeImg", method = RequestMethod.GET)
//    public void createBarCodeMatrix(HttpServletRequest request, HttpServletResponse response, String url) {
//        try {
//            // 设置不缓存图片
//            response.setHeader("Pragma", "No-cache");
//            response.setHeader("Cache-Control", "No-cache");
//            response.setDateHeader("Expires", 0);
//            // 指定生成的响应图片,一定不能缺少这句话,否则错误.
//            response.setContentType("image/gif");
//            ImageIO.write(CodeProduceUtil.toQRCodeMatrix(url, null, null), "gif", response.getOutputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 微信支付 异步通知
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/wechatPayReturn", method = RequestMethod.POST)
//    @ResponseBody
//    public String wechatPayReturn(HttpServletRequest request) {
//        try {
//
////            User userSess = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
////            int user_id = userSess.getId();
//
//            String resultXML = PayUtils.convertStreamToString(request.getInputStream());
//            System.out.println(resultXML);
//            logger.info("微信异步返回交易信息：" +  resultXML);
//
//            Document doc = null;
//            try {
//                doc = DocumentHelper.parseText(resultXML);
//            } catch (DocumentException e) {
//                e.printStackTrace();
//                return "FAIL";
//            }
//            List<String> signParams = Lists.newArrayList();
//            Element rootElt = doc.getRootElement(); // 获取根节
//            String return_code = rootElt.element("return_code").getTextTrim(); //是否成功
//            logger.info("微信异步返回状态：" +  return_code);
//            if ("SUCCESS".equals(return_code)) {
//                List<Element> elements = rootElt.elements();
//                for (Element element : elements) {
//                    if ("sign".equals(element.getName())) {
//                        continue;
//                    }
//                    signParams.add(element.getName() + "=" + element.getStringValue());
//                }
//            }
//            //校验签名是否错误
//            String returnSign = rootElt.element("sign").getTextTrim(); //获取传过来的签名
//            //获取传过来的签名
//            String sign = SignUtil.getSign(signParams, WechatConfig.tradeType.NATIVE.getKey());
//            if (!returnSign.equals(sign)) {
//                return "FAIL";
//            }
//            String out_trade_no = rootElt.element("out_trade_no").getTextTrim(); //订单号
//            logger.info("out_trade_no：" +  out_trade_no);
//            PayOrder payOrder = new PayOrder();
//            payOrder.setOrder_no(out_trade_no);
//            PayOrder db_payOrder = payOrderService.selectOne(payOrder);
//            if (db_payOrder == null) {
//                return "FAIL";
//            } else {
//                if (db_payOrder.getStatus() == PayOrder.Status.SUCCESS) {
//                    return "SUCCESS";
//                }
//                //更改订单状态
//                int r = updateOrderStatus(out_trade_no);
//                if (0 == r) {
//                    return "FAIL";
//                }
//                //更改用户余额
//                int user_id = db_payOrder.getUser_id();
//                String total_fee = rootElt.element("total_fee").getTextTrim();
//
//                double d_total_fee = Double.valueOf(total_fee);
//                double d = Arith.div(d_total_fee,100.00);
//                logger.info("微信支付交易金额：" +  d);
//                int rr = updateBalance(user_id, d);
//                if (0 == rr) {
//                    return "FAIL";
//                }
//                return "SUCCESS";
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return "FAIL";
//        }
//    }
//
//    /**
//     * 更改订单状态
//     *
//     * @param order_no
//     * @return
//     */
//    private int updateOrderStatus(String order_no) {
//        //业务处理
//        PayOrder up_payOrder = new PayOrder();
//        up_payOrder.setStatus(PayOrder.Status.SUCCESS);
//        up_payOrder.setPay_time(DateUtils.getCurrentTime(DateUtils.DEFAULT_FORMAT));
//        Example example = new Example(PayOrder.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("order_no", order_no);
//        return payOrderService.updateByExampleSelective(up_payOrder, example);
//    }
//
//    /**
//     * 更新用户余额
//     *
//     * @param user_id
//     * @param amount
//     * @return
//     */
//    private int updateBalance(Integer user_id, Double amount) {
//        User db_user = userService.selectUserById(user_id);
//        double available_amount = db_user.getAvailable_amount();
//        double curr_total = Arith.add(amount, available_amount);
//        db_user.setAvailable_amount(curr_total);
//        return userService.updateByPrimaryKeySelective(db_user);
//    }
//
//    /**
//     * 微信调度 查询支付状态
//     * @param order_no
//     * @return
//     */
//    @RequestMapping(value = "/callback", method = RequestMethod.GET)
//    @ResponseBody
//    public AjaxRepopnse callback(String order_no){
//        AjaxRepopnse ajaxRepopnse = new AjaxRepopnse();
//        try {
//            logger.info("微信支付回调查看状态 order_no : " + order_no);
//            PayOrder payOrder = new PayOrder();
//            payOrder.setOrder_no(order_no);
//            payOrder = payOrderService.selectOne(payOrder);
//            int state = payOrder.getStatus();
//            if (1== state){
//                ajaxRepopnse.setData("true");
//            }else {
//                ajaxRepopnse.setData("false");
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//            ajaxRepopnse.setData(MessageUtils.message("10010"));
//        }
//        return ajaxRepopnse;
//    }
//
//    /**
//     * 查询交易记录
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "chargelog", method = RequestMethod.POST)
//    @Auth(verifyLogin = true)
//    @ResponseBody
//    public AjaxRepopnse chargelog(@RequestParam String oper_type, HttpServletRequest request, int iDisplayStart, int iDisplayLength) {
//        AjaxRepopnse ajaxRepopnse = new AjaxRepopnse();
//        try {
//            Map<String, Object> params = new HashMap<String, Object>();
//            User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
//            String startTime = request.getParameter("startTime");
//            String endTime = request.getParameter("endTime");
//            if (!StringUtils.isEmpty(startTime)) {
//                params.put("startTime", startTime);
//            }
//            if (!StringUtils.isEmpty(endTime)) {
//                params.put("endTime", endTime);
//            }
//            int id = user.getId();
//            params.put("user_id", id);
//            if (!StringUtils.isEmpty(oper_type)) {
//                if ("add".equals(oper_type)) {
//                    params.put("oper_type", "充值");
//                }
//                if ("spend".equals(oper_type)) {
//                    params.put("oper_type", "消费");
//                }
//            }
//            List<PayOrder> charges = payOrderService.getCharge(params,iDisplayStart,iDisplayLength);
//            PageInfo page = new PageInfo(charges);
//            int totalSize = (int) page.getTotal();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("recordsTotal", charges.size());
//            jsonObject.put("recordsFiltered", totalSize);
//            jsonObject.put("data", charges);
//            ajaxRepopnse.setData(jsonObject.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            ajaxRepopnse.setCode(Response.KO);
//            ajaxRepopnse.setData(MessageUtils.message("10010", null));
//        }
//        return ajaxRepopnse;
//    }
//}
