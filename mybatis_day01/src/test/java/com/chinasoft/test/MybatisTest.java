package com.chinasoft.test;

import com.chinasoft.domain.User;
import com.chinasoft.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    IUserMapper mapper;
    SqlSession sqlSession;
    InputStream in;

    @Before
    public void init() throws IOException{

        //1.读取mybatis的配置文件
         in = Resources.getResourceAsStream("mybatis.xml");
        //2.创建sqlsessionFactory对象      工厂模式
        SqlSessionFactoryBuilder factory = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = factory.build(in);
        //3.创建sqlsession对象
         sqlSession =build.openSession();
        //4.创建IUserMapper的代理对象      代理模式
         mapper = sqlSession.getMapper(IUserMapper.class);



    }
    @After
    public void destory() throws IOException{

        //6.关闭资源
        sqlSession.close();
        in.close();


    }
    @Test
    public void test01() throws IOException {

        //5.通过代理对象执行对应方法
        List<User> all = mapper.findAll();
        System.out.println(all);
        //6.关闭资源
        sqlSession.close();
        in.close();


    }
    //增加user信息
    @Test
    public void test02() throws IOException {
        //1.读取mybatis的配置文件
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        //2.创建sqlsessionFactory对象      工厂模式
        SqlSessionFactoryBuilder factory = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = factory.build(in);
        //3.创建sqlsession对象
        SqlSession sqlSession =build.openSession();
        //4.创建IUserMapper的代理对象      代理模式
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        //5.通过代理对象执行对应方法
        User user = new User(12,"张三",new Date(),18,"男","软件园");
        mapper.addUser(user);
        //获取自动生成的id
        System.out.println(user.getId());
        //提交
        sqlSession.commit();
        //6.关闭资源
        sqlSession.close();
        in.close();

    }
    //删除一条user信息
    @Test
    public void test03() throws IOException {

       mapper.deleteUserById(31);
       sqlSession.commit();


    }
    //修改一条user信息
    @Test
    public void test04(){
        User user = new User(30,"孙策",new Date(),18,"男","软件园");

        mapper.updateUser(user);
        sqlSession.commit();


    }
    //根据id查询user
    @Test
    public void test05(){

        User user = mapper.findUserById(30);
        System.out.println(user);

    }
    /*根据用户名查询用户信息
    * */
    @Test
    public void test06(){
        User user= new User();
        user.setUserName("韩");
        List<User> userByCondition = mapper.findUserByCondition(user);


    }
}
