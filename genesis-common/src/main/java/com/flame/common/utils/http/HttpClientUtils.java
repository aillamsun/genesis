package com.flame.common.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @包名 com.sanfenqiu.common.utils
 * @类名: HttpClientUtils
 * @描述: http client请求工具类
 * @作者： 孙刚
 * @创建时间： 2014年9月21日 上午10:41:19
 * 
 * @修改人：xxx
 * @修改时间:xxxx-xx-xx
 * 
 */
public class HttpClientUtils {

	/**
	 * 默认的编码,解决中文乱码
	 */
	public static String defaultEncode = "UTF-8";

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @return 响应体
	 */
	public static String getSendGet(String url, Map<String, String> paramMap) {
		return getSendGet(url, paramMap, defaultEncode);
	}

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @param encode
	 *            编码
	 * @return 响应体
	 */
	public static String getSendGet(String url, Map<String, String> paramMap, String encode) {
		StringBuffer buf = new StringBuffer();
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		if (paramMap.size() > 0) {
			NameValuePair[] params = new NameValuePair[paramMap.size()];
			Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				Entry<String, String> map = (Entry<String, String>) it.next();
				params[i] = new NameValuePair(map.getKey(), map.getValue());
				i++;
			}
			getMethod.setQueryString(params); // post请求参数用setQueryString
		}
		try {
			client.executeMethod(getMethod);
			byte[] responseBody = getMethod.getResponseBody();
			String content = new String(responseBody, encode);
			buf.append(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return buf.toString();
	}

	/**
	 * 发送Post请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @return 响应体
	 */
	public static String getSendPost(String url, Map<String, String> paramMap) {
		return getSendPost(url, paramMap, defaultEncode);
	}

	/**
	 * 发送Post请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @return 响应体
	 */
	public static String getSendPost(String url, Map<String, String> paramMap, String encode) {
		StringBuffer buf = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encode);
		if (paramMap.size() > 0) {
			NameValuePair[] params = new NameValuePair[paramMap.size()];
			Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				Entry<String, String> map = (Entry<String, String>) it.next();
				params[i] = new NameValuePair(map.getKey(), map.getValue());
				i++;
			}
			postMethod.setRequestBody(params); // post请求参数用setRequestBody
		}
		try {
			client.executeMethod(postMethod);
			byte[] responseBody = postMethod.getResponseBody();
			String content = new String(responseBody, encode);
			buf.append(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return buf.toString();
	}

	/**
	 * 发送Post请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @return 响应体
	 */
	public static String getSendPut(String url, Map<String, String> paramMap) {
		return getSendPut(url, paramMap, defaultEncode);
	}
	
	/**
	 * 发送Put请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @param encode
	 *            编码
	 * @return 响应体
	 */
	public static String getSendPut(String url, Map<String, String> paramMap, String encode) {
		StringBuffer buf = new StringBuffer();
		HttpClient client = new HttpClient();
		
		HttpClientParams clientParams = client.getParams();  
		clientParams.setContentCharset("UTF-8");
		
		PutMethod putMethod = new PutMethod(url);
		if (paramMap.size() > 0) {
			NameValuePair[] params = new NameValuePair[paramMap.size()];
			Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				Entry<String, String> map = (Entry<String, String>) it.next();
				params[i] = new NameValuePair(map.getKey(), map.getValue());
				i++;
			}
			putMethod.setQueryString(params); // put请求参数用setQueryString
		}
		try {
			client.executeMethod(putMethod);
			byte[] responseBody = putMethod.getResponseBody();
			String content = new String(responseBody, encode);
			buf.append(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			putMethod.releaseConnection();
		}
		return buf.toString();
	}
	
	/**
	 * 发送Delete请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @return 响应体
	 */
	public static String getSendDelete(String url, Map<String, String> paramMap) {
		return getSendDelete(url, paramMap, defaultEncode);
	}
	
	/**
	 * 发送Delete请求
	 * 
	 * @param url
	 *            请求路径
	 * @param paramMap
	 *            参数
	 * @param encode
	 *            编码
	 * @return 响应体
	 */
	public static String getSendDelete(String url, Map<String, String> paramMap, String encode) {
		StringBuffer buf = new StringBuffer();
		HttpClient client = new HttpClient();
		
		HttpClientParams clientParams = client.getParams();  
		clientParams.setContentCharset("UTF-8");
		
		DeleteMethod putMethod = new DeleteMethod(url);
		if (paramMap.size() > 0) {
			NameValuePair[] params = new NameValuePair[paramMap.size()];
			Iterator<Entry<String, String>> it = paramMap.entrySet().iterator();
			int i = 0;
			while (it.hasNext()) {
				Entry<String, String> map = (Entry<String, String>) it.next();
				params[i] = new NameValuePair(map.getKey(), map.getValue());
				i++;
			}
			putMethod.setQueryString(params); // put请求参数用setQueryString
		}
		try {
			client.executeMethod(putMethod);
			byte[] responseBody = putMethod.getResponseBody();
			String content = new String(responseBody, encode);
			buf.append(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			putMethod.releaseConnection();
		}
		return buf.toString();
	}
	
	
	
	
	
	/**
	 * HttpClient  上传文件
	 * @param files
	 * @return
	 */
    public static boolean uploadFile(List<File> files, String uploadUrl) {
    	Part[] parts = new Part[files.size()];
    	int k = 0;
    	for(File file:files){
    		if (file.exists()) {
    			FilePart fp = null;
				try {
					fp = new FilePart("fileName", file);
					parts[k] = fp;
	    			k++;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
            }
    	}
        PostMethod postMethod = new PostMethod(uploadUrl);
        try {
            //对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
               	return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
    }
    
    
    
    /**
     * base64上传文件
     * @return
     */
    public static String base64UploadFile(String[] imgSource, String filePath, String uploadUrl) {
    	MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
		manager.getParams().setSoTimeout(60000);
		manager.getParams().setDefaultMaxConnectionsPerHost(5);
		manager.getParams().setMaxTotalConnections(40);

		HttpClient client = new HttpClient(manager);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");//指定传送字符集为utf-8格式
		PostMethod postMethod = new PostMethod(uploadUrl);
		postMethod.addRequestHeader("Cache-Control", "no-cache");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		NameValuePair[] data = new NameValuePair[imgSource.length+1];
		for(int i=0;i<imgSource.length;i++){
			data[i] = new NameValuePair("imgSource",imgSource[i]);//请求参数
		}
		data[imgSource.length] = new NameValuePair("filePath",filePath);//请求参数
		postMethod.setRequestBody(data);
		try {
			client.executeMethod(postMethod);
			String content = postMethod.getResponseBodyAsString();
			postMethod.releaseConnection();
			System.out.println("ResponseBody:\n"+content);//返回结
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,Object> re = Maps.newConcurrentMap();
			re.put("code",500);
			re.put("message","系统错误");
			return JSONObject.toJSONString(re);
		}
    }
    
    
    
 
	
	
    /**
     * 
        * @Title: getSendBody
        * @Description: TODO 直接 请求BODY
        * @param @param url
        * @param @param xml
        * @param @param encode
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
	public static String getSendBody(String url, String xml, String encode) {
		StringBuffer buf = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encode);
		postMethod.setRequestBody(xml);
		try {
			client.executeMethod(postMethod);
			byte[] responseBody = postMethod.getResponseBody();
			String content = new String(responseBody, encode);
			buf.append(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return buf.toString();
	}


}
