package com.cl.smm6.common.uitl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DataUtil {

	/**
	 * 密码采用md5加密
	 */
	public static String MD5(String src) {
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
					'F' };
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] target = digest.digest(src.getBytes());
			for (byte b : target) {
				// 取高四位数据
				buffer.append(chars[(b >> 4) & 0x0f]);
				// 去第四位数据
				buffer.append(chars[b & 0x0f]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 密码采用md5加密
	 */
	public static String md5(String src) {
		try {
			// src不空才加密
			if (src != null) {
				StringBuffer buffer = new StringBuffer();
				char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
						'f' };
				MessageDigest digest = MessageDigest.getInstance("MD5");
				byte[] target = digest.digest(src.getBytes());
				for (byte b : target) {
					// 取高四位数据
					buffer.append(chars[(b >> 4) & 0x0f]);
					// 去第四位数据
					buffer.append(chars[b & 0x0f]);
				}
				return buffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Serializable deeplyCopy(Serializable src) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(src);
			oos.close();
			bos.close();
			byte[] data = bos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Serializable result = (Serializable) ois.readObject();
			ois.close();
			bis.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 产生随机字符串
	 */
	public static final String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
					.toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
		}
		return new String(randBuffer);
	}

	/**
	 * 数组转换为逗号隔开的String
	 * @author L
	 * @date 2016年1月21日
	 * @param args
	 * @return
	 */
	public static String arrToStr(Object[] args) {
		if (ValidateUtil.isValid(args)) {
			String temp = "";
			for (Object s : args) {
				temp = temp + s + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
		return null;
	}
	
	/**
	 * 根据最大部门编号，得到下一个部门编号,maxCode不能超过99
	 * @author L
	 * @date 2016年1月28日
	 * @param maxCode
	 * @return
	 */
	public static Integer getNextDepCode(Integer maxCode){
		int i=0;
		int x=0;
		while(maxCode%10==0){
			maxCode=maxCode/10;
			i++;
		}
		maxCode++;
		while(maxCode%10==0){
			maxCode=maxCode/10;
			x++;
		}
		if(x>1){
			return null;
		}else {
			for(int y=0;y<x;y++){
				maxCode=maxCode*10;
			}
			for(int j=0;j<i;j++){
				maxCode=maxCode*10;
			}
			return maxCode;
		}
	}
	
	/**
	 * 根据父code，得到第一个子code
	 * @author L
	 * @date 2016年1月28日
	 * @param prentCode
	 * @return
	 */
	public static Integer getFirstDepCode(Integer prentCode){
		int i=0;
		while(prentCode%10==0){
			prentCode=prentCode/10;
			i++;
		}
		if(i>1){
			for(int j=0;j<2;j++){
				prentCode=prentCode*10;
			}
			prentCode++;
			for(int j=0;j<i-2;j++){
				prentCode=prentCode*10;
			}
			return prentCode;
		}else {
			return null;
		}
	}

	/**
	 * 根据用户部门code得到其部门及其子部门codeP（去掉后面0）
	 * @author L
	 * @date 2016年2月5日
	 * @param sysUser
	 * @return
	 */
	public static Integer getDepCodePByDepCode(Integer depCode) {
		while(depCode%10==0){
			depCode=depCode/10;
		}
		return depCode;
	}
	
	/**
	 * 得到childJson
	 * @author L
	 * @date 2016年2月16日
	 * @param list
	 * @return
	 */
	public static List<HashMap<String, Object>> getChildJson(List<HashMap<String, Object>> list){
		List<HashMap<String, Object>> list2=new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> sysMenu1;
		Set<Integer> childrenIds=new HashSet<Integer>();
		for(int i=0;i<list.size();i++){
			List<HashMap<String, Object>> children=new ArrayList<>();
			HashMap<String, Object> sysMenu2 = null;
			sysMenu1= list.get(i);
			for(int j=0;j<list.size();j++){
				sysMenu2=list.get(j);
				if((sysMenu1.get("id")==sysMenu2.get("_parentId"))
						||(sysMenu1.get("id").equals(sysMenu2.get("_parentId")))){
					children.add(sysMenu2);
					childrenIds.add((Integer) sysMenu2.get("id"));
				}
				sysMenu1.put("children", children);
			}
			if(!childrenIds.contains(sysMenu1.get("id"))){
				list2.add(sysMenu1);
			}
		}
		return list2;
	}

}
