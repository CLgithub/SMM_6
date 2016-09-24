package com.cl.smm6.common.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

public class BaseEntity implements Serializable {

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		String className = this.getClass().getSimpleName();
		buffer.append(className + "{");
		// 获取所有的字段
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			Class ftype = null;
			for (Field f : fields) {
				// 获取字段的类型
				ftype = f.getType();
				if (ftype.isPrimitive() || ftype == String.class || ftype == Integer.class || ftype == Long.class
						|| ftype == Date.class && Modifier.isStatic(ftype.getModifiers())) {
					f.setAccessible(true);
					buffer.append(f.getName() + ":" + f.get(this) + ",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		buffer.append("}");
		return buffer.toString();
	}
}
