package com.flame.core.pay.common;


import com.flame.common.utils.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;


public class PayUtils {

    private static ResourceBundle Payparam = null;

    /**
     * 获得配置参数
     *
     * @param key
     * @return
     */
    public static String getPayparam(String key) {
        try {
            Payparam = ResourceBundle.getBundle("onlinepay");
            String s = Payparam.getString(key);
            if (StringUtils.isBlank(s)) {
                s = "";
            }
            s = new String(s.getBytes("iso-8859-1"), "UTF-8");
            return StringUtils.trimToEmpty(s);
        } catch (Exception e) {
            System.out.println(DateUtils.getStringDateShort() + "key:" + key + " 在支付配置资源文件中不存在");
            return "";
        }
    }


    public static String getWebRoot() {
        String serverhost = getPayparam("SERVERHOST");
        String serverport = getPayparam("SERVERPORT");
        if (StringUtils.isBlank(serverport) || "80".equals(serverport)) {
            return "http://" + serverhost;
        } else {
            return "http://" + serverhost + ":" + serverport;
        }
    }

    /**
     * 相对路径文件，转为绝对路径文件
     *
     * @param RelativeFile
     * @return
     */
    public static String getAbsoluteFile(String RelativeFile) {
        String path = PayUtils.class.getResource("/").getPath().toString();
        path = path.replace('\\', '/');
        String os = System.getProperties().getProperty("os.name");
        if (StringUtils.isNotBlank(os) && os.toUpperCase().indexOf("WIN") >= 0) {
            path = path.substring(1, path.length());
        }
        return path + RelativeFile;
    }

    /**
     * 元转换为分
     *
     * @return
     */
    public static long YuanToFen(double money) {
        BigDecimal b1 = new BigDecimal(Double.toString(money));
        BigDecimal b2 = new BigDecimal(new Integer(100).toString());
        return b1.multiply(b2).longValue();
    }

    /**
     * 分转换成元 100分==1元
     *
     * @param money
     * @return double
     */
    public static double Fen2Yuan(long money) {
        BigDecimal b1 = new BigDecimal(Long.toString(money));
        BigDecimal b2 = new BigDecimal(new Integer(100).toString());
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 格式化数字，小数位保证2位，不足补0
     *
     * @param money
     * @return
     */
    public static String FmtNum(double money) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(money);
    }


    /**
     * 获取POST过来反馈信息
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> resolveRequest(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        Iterator iter = requestParams.keySet().iterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            System.out.println(name + ":" + valueStr);
            params.put(name, valueStr);
        }
        return params;
    }


    // get参数拼接后字符串
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }


    /**
     * 返回的转为map结构
     *
     * @return
     */
    public static Map<String, String> resultMap(String queryReturn) {
        Map<String, String> m = new TreeMap<String, String>();
        if (StringUtils.isNotBlank(queryReturn)) {
            String[] resultSplit = queryReturn.split("\n");
            for (String resultLine : resultSplit) {
                String[] result = resultLine.split("=");
                if (result.length > 1) {
                    m.put(result[0], result[1]);
                }
            }
        }
        return m;
    }

    /**
     * 将map转换为原始记录集
     */
    @SuppressWarnings("rawtypes")
    public static String mapToString(Map<String, String> map) {
        Set<String> keys = map.keySet();
        String info = "";
        for (Iterator it = keys.iterator(); it.hasNext(); ) {
            String st = (String) it.next();
            info += st + ":" + map.get(st) + "\n";
        }
        return info;
    }


    /**
     * 把参数转为MAP
     * @return
     */
//	public  static Map<String, String> getParamMap(HttpServletRequest request) {
//		Map<String, String> params = new HashMap<String, String>();
//		@SuppressWarnings("unchecked")
//		Map<String,Object> requestParams = request.getParameterMap();
//		Iterator<String> iter = requestParams.keySet().iterator();
//		while (iter.hasNext()) {
//			String name = iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//			}
//			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//			params.put(name, valueStr);
//		}
//		return params;
//	}

    /**
     * 流转字符串
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
