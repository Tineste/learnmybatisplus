package orz.xuchao.learnmybatisplus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import orz.xuchao.learnmybatisplus.entity.User;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAR {

    @Test
    public void insert(){
        User user=new User();
        user.setName("李雷");
        user.setAge(30);
        user.setCreateTime(LocalDateTime.now());
        boolean resoult=user.insert();
        System.out.println(resoult);
    }
    @Test
    public void selectById(){
        User user=new User();
//        User user1=user.selectById(1184349042275909633L);
        user.setId(1184349042275909633L);
        User user1=user.selectById();
        System.out.println(user1.toString());
    }
    @Test
    public void updateById(){
        User user=new User();
        user.setId(1184349042275909633L);
        user.setName("韩梅梅");
        boolean resoult=user.updateById();
        System.out.println(resoult);

    }

    @Test
    public void insertOrUpdate(){
        User user=new User();
        user.setId(1184349042275909632L);
        user.setName("BB");
        user.setAge(10);
        boolean resoult=user.insertOrUpdate();
        System.out.println(resoult);
    }


}
