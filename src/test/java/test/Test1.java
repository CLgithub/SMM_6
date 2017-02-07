package test;

import com.cl.smm6.common.mapper.SysLogMapper;
import com.cl.smm6.common.mapper.SysUserMapper;
import com.cl.smm6.system.service.SysLogService;
import com.cl.smm6.system.service.SysUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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
        SysUserService sysUserService= (SysUserService) applicationContext.getBean("sysUserServiceImpl");
//        SysDepartmentService SysDepartmentServiceImpl= (SysDepartmentService) applicationContext.getBean("sysDepartmentServiceImpl");
//        SysRightsService sysRightsService= (SysRightsService) applicationContext.getBean("sysRightsServiceImpl");


        System.out.println("第一次查询日志："+syslogmapper.getLogsPBBySearch(1,1));
        System.out.println("第一次查询用户："+sysUserService.getUserPBBySearch(null,3,1,null));

//        SysLog sysLog=new SysLog();
//        sysLog.setOperator("aaa");
//        sysLog.setOpertime(new Date());
//        syslogmapper.insert0(sysLog);
//        System.out.println("插入日志");

//        long l1=System.currentTimeMillis();
        long l1=System.currentTimeMillis();
        System.out.println("第二次查询日志："+syslogmapper.getLogsPBBySearch(1,1));
        long l2=System.currentTimeMillis();
        System.out.println("第二次查询用户："+sysUserService.getUserPBBySearch(null,3,1,null));
        long l3=System.currentTimeMillis();

        System.out.println("第二次查询日志时间："+(l2-l1));
        System.out.println("第二次查询用户时间："+(l3-l2));

    }


}
