package com.flame.common.utils.security;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 可以进行MD5和SHA1加密计算
 */
public final class MD5Utils {

    /**
     * 16进制数值
     */

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     *
     * 日志
     */

//	private static final Log log = LogFactory.getLog(MD5Utils.class);

    /**
     * 生成MD5加密校验码
     *
     * @param string 待加密字符串
     * @return MD5加密校验码
     * @since 0.2
     */

    public static String md5(String string) {

        return encryptString(getEncrypt("MD5"), string);

    }

    /**
     * 生成MD5加密校验码
     *
     * @param file 待加密文件
     * @return MD5加密校验码
     * @since 0.2
     */

    public static String md5(File file) {

        return encryptFile(getEncrypt("MD5"), file);

    }

    /**
     * 获取MD5再次换位的字符串
     *
     * @param string
     * @return
     */
    public static String md5Passwd(String string) {
        String pass = md5(string);
        return pass.substring(11) + pass.substring(0, 11);
    }

    /**
     * 生成SHA1加密校验码
     *
     * @param string 待加密字符串
     * @return SHA1加密校验码
     * @since 0.2
     */

    public static String sha1(String string) {

        return encryptString(getEncrypt("SHA1"), string);

    }

    /**
     * 生成SHA1加密校验码
     *
     * @param file 待加密文件
     * @return SHA1加密校验码
     * @since 0.2
     */

    public static String sha1(File file) {

        return encryptFile(getEncrypt("SHA1"), file);

    }

    /**
     * 获得指定的算法加密器
     *
     * @param algorithm 算法
     *                  如果没有参数algorithm指定的加密算法则抛出此异常
     * @return 加密器
     * @since 0.2
     */

    private static MessageDigest getEncrypt(String algorithm) {

        try {

            return MessageDigest.getInstance(algorithm);

        } catch (NoSuchAlgorithmException ex) {

//            log.error("无法创建" + algorithm + "算法加密器", ex);
            return null;

        }

    }

    /**
     * 计算结果转为16进制表示
     *
     * @param bytes 待转换Byte数组
     * @return 转换结果
     * @since 0.2
     */

    private static String bytesToHex(byte[] bytes) {

        int length = bytes.length;

        StringBuilder sb = new StringBuilder(2 * length);

        for (int i = 0; i < length; i++) {

            sb.append(hexDigits[(bytes[i] & 0xf0) >> 4]);

            sb.append(hexDigits[bytes[i] & 0xf]);

        }

        return sb.toString();

    }

    /**
     * 使用加密器对目标字符串进行加密
     *
     * @param digest 加密器
     * @param string 目标字符串
     * @return 计算结果
     * @since 0.2
     */

    private static String encryptString(MessageDigest digest, String string) {

        return bytesToHex(digest.digest(string.getBytes()));

    }

    /**
     * 使用加密器对目标文件进行加密
     *
     * @param digest 加密器
     * @param file   目标文件
     *               <p>
     *               当文件未找到或读取错误时抛出此异常
     * @return 计算结果
     * @since 0.2
     */

    private static String encryptFile(MessageDigest digest, File file) {

        InputStream fis = null;

        try {

            fis = new FileInputStream(file);

            byte[] buffer = new byte[1024];

            int numRead = 0;

            while ((numRead = fis.read(buffer)) > 0) {

                digest.update(buffer, 0, numRead);

            }

        } catch (FileNotFoundException ex) {

//			log.error("文件" + file.getName() + "未找到", ex);

            return null;

        } catch (IOException ex) {

//			log.error("文件" + file.getName() + "发生I/O错误", ex);

            return null;

        } finally {

            try {

                fis.close();

            } catch (IOException ex) {

//				log.error("无法关闭文件" + file.getName(), ex);

                return null;

            }

        }

        return bytesToHex(digest.digest());

    }

    public static void main(String[] args) {
        System.out.print(md5("123abc"));
    }

}
