package test;

import com.cl.smm6.common.entity.SysUser;
import com.cl.smm6.common.mapper.SysLogMapper;
import com.cl.smm6.common.mapper.SysRightsMapper;
import com.cl.smm6.common.mapper.SysUserMapper;
import com.cl.smm6.system.service.SysDepartmentService;
import com.cl.smm6.system.service.SysLogService;
import com.cl.smm6.system.service.SysRightsService;
import com.cl.smm6.system.service.SysUserService;
import com.cl.smm6.system.serviceimpl.SysDepartmentServiceImpl;
import com.cl.smm6.system.serviceimpl.SysUserServiceImpl;
import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 17/1/3.
 */
public class Test1 {

    public static void test1(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-context.xml");
//        SysUserService sysUserService= (SysUserService) applicationContext.getBean("sysUserServiceImpl");
        SysUserMapper sysUserMapper= (SysUserMapper) applicationContext.getBean("sysUserMapper");
        SysLogMapper sysLogMapper= (SysLogMapper) applicationContext.getBean("sysLogMapper");

//        System.out.println(sysUserMapper);
//        System.out.println(sysLogMapper);

        List<String> logTabNames = sysLogMapper.getLogTabNames();
        StringBuffer stringBuffer=new StringBuffer();
        for(String tabName:logTabNames){
            stringBuffer.append("select * from "+tabName);
            stringBuffer.append(" union ");
        }
        String sql= stringBuffer.toString();
        sql =sql.substring(0,sql.length()-6);
        System.out.println(sql);
    }

    public static void main(String[] args){
        getSettUnitBySettUnitIdTest();
    }


    public static void getSettUnitBySettUnitIdTest() {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-context.xml");
        SysLogService syslogmapper= (SysLogService) applicationContext.getBean("sysLogServiceImpl");
        SysDepartmentService SysDepartmentServiceImpl= (SysDepartmentService) applicationContext.getBean("sysDepartmentServiceImpl");

        System.out.println(syslogmapper.getLogsPBBySearch(1,21));
        System.out.println(syslogmapper.getLogsPBBySearch(1,21));
        System.out.println(SysDepartmentServiceImpl);

    }


}
