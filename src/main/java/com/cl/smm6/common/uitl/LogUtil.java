package com.cl.smm6.common.uitl;

import java.util.Calendar;

public class LogUtil {

	// /**
	// * 动态生成日志表名
	// * @param offSet 0:本月 1:下个月 -1：上个月
	// */
	// public static String generateLogTableName0(int offSet) {
	// Calendar c = Calendar.getInstance();
	// int year = c.get(Calendar.YEAR);
	// int month = c.get(Calendar.MONTH) + 1 + offSet;
	//
	// int i=(offSet)/12;
	// System.out.println(i);
	//
	// if (month > 12) {//一年以后
	// year=year+1;
	// month = month - 12;
	// } else if (month < 1) {
	// year=year-((-i)+1);
	// month = month + 12*((-i)+1);
	// }
	// return "sys_log_" + year + "_" + month;
	// }
	//
	//
	// /**
	// * 动态生成日志表名
	// * @param offSet 0:本月 1:下个月 -1：上个月
	// */
	// public static String generateLogTableName1(int offSet) {
	// Calendar c = Calendar.getInstance();
	// int year = c.get(Calendar.YEAR);
	// int month = c.get(Calendar.MONTH) + 1;
	// if(offSet>=0){//向未来
	// int i=(month+offSet)/12;
	// year=year+i;
	// month=(month+offSet)%12==0?month:(month+offSet)%12;
	// }else {//向曾经
	// if(month+offSet<=0){
	// int i=(month+offSet)/(-12)+1;
	// year=year-i;
	// month=month+offSet==0?12:12+(month+offSet)%(-12);
	// }else {
	// month=month+offSet;
	// }
	// }
	// return "sys_log_" + year + "_" + month;
	// }

	/**
	 * 动态生成日志表名
	 * @param offSet 0:本月 1:下个月 -1：上个月
	 */
	public static String generateLogTableName(int offSet) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1 + offSet;
		if (month > 12) {
			year += month / 12;
			month = month % 12;
		}
		int n=0;//为了确保12*n+month>0
		for(n=0;12*n+month<0;n++){
			n++;
		}
		if (month < 1) {
			year -= (month / (-12) + 1);
			month = (12*n + month) % (-12) == 0 ? 12 : (12*n + month) % (-12);// 相当于n个12-offset再加上原来的month
		}
		return "sys_log_" + year + "_" + month;
	}

}
