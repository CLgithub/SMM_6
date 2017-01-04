package com.cl.smm6.test;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.system.service.SysUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chenlei on 2017/1/3.
 */
public class Test1 {

    @Test
    public void test1(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-context.xml");
        SysUserService sysUserService= (SysUserService) applicationContext.getBean("sysUserServiceImpl");
        System.out.println(sysUserService);
        SysUser sysUser=sysUserService.doLogin("admin","1111");
        System.out.println(sysUser);
    }

}
