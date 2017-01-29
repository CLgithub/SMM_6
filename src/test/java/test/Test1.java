package test;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapper.SysRightsMapper;
import com.cl.smm6.common.mapper.SysUserMapper;
import com.cl.smm6.system.service.SysRightsService;
import com.cl.smm6.system.service.SysUserService;
import com.cl.smm6.system.serviceimpl.SysUserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by L on 17/1/3.
 */
public class Test1 {

    public static void test1(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-context.xml");
//        SysUserService sysUserService= (SysUserService) applicationContext.getBean("sysUserServiceImpl");
//        SysUserMapper sysUserMapper= (SysUserMapper) applicationContext.getBean("sysUserMapper");
        SysRightsMapper sysRightsMapper= (SysRightsMapper) applicationContext.getBean("sysRightsMapper");

        List<Map<String, Object>> maxRPosAndMaxRCode = sysRightsMapper.getMaxRPosAndMaxRCode();
        System.out.println(maxRPosAndMaxRCode);
    }

    public static void main(String[] args){
        test1();
    }

}
