package com.flame.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 */

public class Arith {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化
	private Arith() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("需要精确到小数点以后几位的值不正确，请检查！");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("需要精确到小数点以后几位的值不正确，请检查！");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String roundFormat(double v, int scale) {
		double dd = round(v, scale);
		// 如果小数位不足，则在后面补0
		String jj = dd + "";
		int k = 0;
		byte[] tempbyte = jj.getBytes();
		for (int i = 0; i < jj.length(); i++) {
			if (tempbyte[i] == 46) {
				k = i;
			}
		}
		String strnext = jj.substring(k + 1, jj.length());// 小数部分
		if (strnext.length() < scale) {
			String s_t = "";
			for (int tk = 0; tk < scale - strnext.length(); tk++) {
				s_t = s_t + "0";
			}
			return dd + s_t;
		} else {
			return dd + "";
		}
	}

	/**
	 * 提供精确的小数位处理，只取几位小数，不做四舍五入处理。
	 * 
	 * @param v
	 *            需要处理的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 处理后的结果
	 */
	public static double roundNo(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("需要精确到小数点以后几位的值不正确，请检查！");
		}
		String jj = Double.toString(v);
		int k = 0;
		byte[] tempbyte = jj.getBytes();
		for (int i = 0; i < jj.length(); i++) {
			if (tempbyte[i] == 46) {
				k = i;
			}
		}

		String strpre = jj.substring(0, k); // 整数部分
		String strnext = jj.substring(k + 1, jj.length());// 小数部分
		if (scale == 0)
			return Double.parseDouble(strpre);
		else {
			return Double.parseDouble(strpre + "." + strnext.substring(0, scale));
		}
	}

	/**
	 * 将科学计数法的DOUBLE转变为正常表示法
	 * 
	 * @param s
	 *            double
	 * @return String
	 */
	public static String getDstr(double s) {
		try {
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			return nf.format(s);
		} catch (Exception es) {
			return "";
		}
	}

	public static String getDstr(String s) {
		try {
			Double d = new Double(s);
			java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			return nf.format(d);
		} catch (Exception es) {
			return "";
		}
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            精确的整数位数,10位是1
	 * @return 四舍五入后的结果
	 */
	public static int round(int v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("需要精确到小数点以后几位的值不正确，请检查！");
		}
		Integer cc = 1;
		for (int i = 0; i < scale; i++) {
			cc = cc * 10;
		}
		double vv = new Double(v) / cc;
		vv = round(vv, 0) * (cc);
		return (int) vv;
	}
	
	public static String formatMoney(Double price){
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("#.00");
		return myformat.format(price);
	}

	public static void main(String[] args) {
		System.out.println(formatMoney(12d));

	}

}
/*
 * 在Java中实现浮点数的精确计算
 * 
 * 问题的提出：
 * 
 * 如果我们编译运行下面这个程序会看到什么？
 * 
 * 
 * 你没有看错！结果确实是 0.060000000000000005 0.5800000000000001 401.49999999999994
 * 1.2329999999999999
 * Java中的简单浮点数类型float和double不能够进行运算。不光是Java，在其它很多编程语言中也有这样的问题。
 * 在大多数情况下，计算的结果是准确的，但是多试几次（可以做一个循环）就可以试出类似上面的错误。现在终于理解为什么要有BCD码了。
 * 这个问题相当严重，如果你有9.999999999999元，你的计算机是不会认为你可以购买10元的商品的。
 * 在有的编程语言中提供了专门的货币类型来处理这种情况，但是Java没有。现在让我们看看如何解决这个问题。
 * 
 * 
 * 四舍五入 我们的第一个反应是做四舍五入。Math类中的round方法不能设置保留几位小数，我们只能象这样（保留两位）： public double
 * round(double value){ return Math.round(value*100)/100.0; }
 * 
 * 非常不幸，上面的代码并不能正常工作，给这个方法传入4.015它将返回4.01而不是4.02，如我们在上面看到的
 * 
 * 4.015*100=401.49999999999994
 * 
 * 因此如果我们要做到精确的四舍五入，不能利用简单类型做任何运算
 * 
 * java.text.DecimalFormat也不能解决这个问题：
 * 
 * 输出是4.02
 * 
 * 
 * 
 * BigDecimal 在《Effective
 * Java》这本书中也提到这个原则，float和double只能用来做科学计算或者是工程计算，在商业计算中我们要用java
 * .math.BigDecimal。BigDecimal一共有4个够造方法，我们不关心用BigInteger来够造的那两个，那么还有两个，它们是：
 * 
 * BigDecimal(double val)
 * 
 * Translates a double into a BigDecimal.
 * 
 * BigDecimal(String val)
 * 
 * Translates the String repre sentation of a BigDecimal into a BigDecimal.
 * 
 * 上面的API简要描述相当的明确，而且通常情况下，上面的那一个使用起来要方便一些。我们可能想都不想就用上了，会有什么问题呢？等到出了问题的时候，
 * 才发现上面哪个够造方法的详细说明中有这么一段：
 * 
 * Note: the results of this constructor can be somewhat unpredictable. One
 * might assume that new BigDecimal(.1) is exactly equal to .1, but it is
 * actually equal to .1000000000000000055511151231257827021181583404541015625.
 * This is so because .1 cannot be represented exactly as a double (or, for that
 * matter, as a binary fraction of any finite length). Thus, the long value that
 * is being passed in to the constructor is not exactly equal to .1, appearances
 * nonwithstanding. The (String) constructor, on the other hand, is perfectly
 * predictable: new BigDecimal(".1") is exactly equal to .1, as one would
 * expect. Therefore, it is generally recommended that the (String) constructor
 * be used in preference to this one.
 * 
 * 
 * 原来我们如果需要精确计算，非要用String来够造BigDecimal不可！在《Effective
 * Java》一书中的例子是用String来够造BigDecimal的，但是书上却没有强调这一点，这也许是一个小小的失误吧。
 * 
 * 解决方案
 * 
 * 现在我们已经可以解决这个问题了，原则是使用BigDecimal并且一定要用String来够造。
 * 
 * 但是想像一下吧，如果我们要做一个加法运算，需要先将两个浮点数转为String，然后够造成BigDecimal，在其中一个上调用add方法，传入另一个作为参数
 * ，然后把运算的结果（BigDecimal）再转换为浮点数。你能够忍受这么烦琐的过程吗？下面我们提供一个工具类Arith来简化操作。它提供以下静态方法，
 * 包括加减乘除和四舍五入：
 */
