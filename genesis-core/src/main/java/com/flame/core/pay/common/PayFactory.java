package com.flame.core.pay.common;

import com.alibaba.fastjson.JSON;
import com.flame.core.pay.alipay.AliPay;
import com.flame.core.pay.union.UnionPay;
import com.flame.core.pay.wechat.WechatPay;


public class PayFactory {


    public enum tradePlat {
        WECHAT,
        ALIPAY,
        UNION;
    }

    /***
     * @param @param  tradePlat
     * @param @return 参数
     * @return Pay    返回类型
     * @throws
     * @Title: getPayHandle
     * @Description: TODO
     */
    public static Pay getPayHandle(tradePlat tradePlat) {
        if (tradePlat == PayFactory.tradePlat.WECHAT) {
            return new WechatPay();
        } else if (tradePlat == PayFactory.tradePlat.ALIPAY) {
            return new AliPay();
        } else {
            return new UnionPay();
        }
    }

    public static void main(String[] args) throws Exception {
        PayResult payResult = PayFactory.getPayHandle(tradePlat.ALIPAY).pay(new PayParam(PayParam.ALIPAY_PC_WEB, 100.0, "123", "test"));
        System.out.print(JSON.toJSON(payResult));
    }
}
