package test;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapper.SysUserMapper;
import com.cl.smm6.system.service.SysUserService;
import com.cl.smm6.system.serviceimpl.SysUserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by L on 17/1/3.
 */
public class Test1 {

    public static void test1(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-context.xml");
        SysUserService sysUserService= (SysUserService) applicationContext.getBean("sysUserServiceImpl");
        SysUserMapper sysUserMapper= (SysUserMapper) applicationContext.getBean("sysUserMapper");
//        System.out.println(sysUserService);
//        System.out.println(sysUserMapper);
//        SysUser sysUser= (SysUser) sysUserMapper.selectUListByLogin("admin","admin");

        System.out.println(sysUserService.selectByPrimaryKey(1));
    }

    public static void main(String[] args){
        test1();
    }

}
