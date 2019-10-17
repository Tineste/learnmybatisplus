package orz.xuchao.learnmybatisplus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import orz.xuchao.learnmybatisplus.entity.User;
import orz.xuchao.learnmybatisplus.service.UserService;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void selectById(){
        User user=userService.getById(1182105164730793986L);
        System.out.println(user.toString());
    }


    @Test
    public void batch(){
        User user1=new User();
        user1.setName("ffff");
        user1.setAge(12);
        User user2=new User();
        user2.setName("gggg");
        user2.setAge(23);
        List<User> userList= Arrays.asList(user1,user2);
        boolean resoult=userService.saveBatch(userList);
        System.out.println(resoult);

    }
    @Test
    public void doit(){
        User user1=new User();
        user1.setId(1184634442261450754L);
        user1.setName("CCC");
        user1.setAge(15);
        User user2=new User();
        user2.setName("eee");
        user2.setAge(25);
        List<User> userList= Arrays.asList(user1,user2);
        boolean resoult=userService.saveOrUpdateBatch(userList);
        System.out.println(resoult);
    }

//    测试乐观锁，读取这个数据然后去更改这个数据
    @Test
    public void testLock(){
        User user=userService.getById(1182105164730793986L);
        int version=user.getVersion();

        User user1=new User();
        user1.setName("大明");
        user1.setAge(5);
        user1.setVersion(version+3);
        user1.setId(1182105164730793986L);
        userService.updateById(user1);

    }

}
