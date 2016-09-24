package com.cl.smm6.common.uitl;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cl.smm6.common.entity.SysRights;
import com.cl.smm6.common.entity.SysUser;

/**
 * 判断是否为空
 * 
 * @author Administrator
 */
public class ValidateUtil {

	/**
	 * 判断字符串是否有效
	 * 
	 * @return false 为空
	 */
	public static boolean isValid(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断集合是否有效
	 * 
	 * @return false 为空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection list) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断数组的有效性
	 */
	public static boolean isValid(Object[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否有权限
	 * @author L
	 * @date 2015年11月26日
	 * @param url  访问地址
	 * @return
	 */
	public static boolean hasRight(String url, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		ServletContext servletContext = httpSession.getServletContext();
		Map<String, SysRights> map = (Map<String, SysRights>) servletContext.getAttribute("all_rights_map");
		SysRights r = map.get(url);
		if (r == null) {
			return false;
		} else {
			SysUser sysUser = (SysUser) httpSession.getAttribute("sysUser");
			// 登录?
			if (sysUser == null) {
				return false;
			} else {
				// 是公共权限吗？
				if (r.getCommon()) {
					return true;
				} else {
					// 有权限?
					if (sysUser.hasRight(r) || "admin".equals(sysUser.getLoginname())) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

}
