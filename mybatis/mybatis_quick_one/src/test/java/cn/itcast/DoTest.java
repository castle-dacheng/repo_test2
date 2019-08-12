package cn.itcast;


import cn.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DoTest {
    //更换配置文件
    @Test
    public void test4() throws IOException {
        User user = new User();
        user.setId(1);
        InputStream resourceAsStream = Resources.getResourceAsStream("cn/sqlMapConfig1.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("userMapper.findOne",user);
        System.out.println(users);
        sqlSession.close();
    }
    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("cn/sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int delete = sqlSession.delete("userMapper.deleteOne");
        System.out.println(delete);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test2() throws IOException {
        //虚拟对象
        User user = new User();
        user.setId(1);
        //加载核心配置文件创建session会话工程
        InputStream resourceAsStream = Resources.getResourceAsStream("cn/sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //根据工厂获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过会话对象操作数据库
        int update = sqlSession.update("userMapper.updateOne",user);
        System.out.println(update);
        //如果是对数据库进行增，改操作进行事务提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test1() throws IOException {
        User user = new User();
        user.setUsername("lucy");
        user.setPassword("abc");
        //得到加载到的核心配置文件的流对象
        InputStream resourceAsStream = Resources.getResourceAsStream("cn/sqlMapConfig.xml");
        //利用加载到的核心配置文件内容，创建sqlSession会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //通过sqlSession会话工厂拿到，sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句操作
        int insert = sqlSession.insert("userMapper.addOne", user);
        System.out.println(insert);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test() throws IOException {
        //得到要加载文件的流对象
        InputStream resourceAsStream = Resources.getResourceAsStream("cn/sqlMapConfig.xml");
        //对流对象进行解析，并创建sqlsession工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sql 会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //进行操作
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }
}
