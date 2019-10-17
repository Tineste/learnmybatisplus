package orz.xuchao.learnmybatisplus;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import orz.xuchao.learnmybatisplus.dao.UserDao;
import orz.xuchao.learnmybatisplus.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdataTest {

    @Autowired
    private UserDao userMapper;

    @Test
    public void updateByIds(){
        User user=new User();
        user.setId(1182090169351630850L);
        user.setName("王大大");
        userMapper.updateById(user);
    }


    @Test
    public void updateByWapper(){
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("name","小强").eq("age",23);
        User user=new User();
        user.setName("大强");
        userMapper.update(user,updateWrapper);
    }


    @Test
    public void updateByWapper3(){
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("name","大强").eq("age",23).set("age",30);
        userMapper.update(null,updateWrapper);
    }
    @Test
    public void updateByWapperLambda(){
        LambdaUpdateWrapper<User> lambdaUpdateWrapper= Wrappers.<User>lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getName,"大强").eq(User::getAge,30).set(User::getAge,31);
        userMapper.update(null,lambdaUpdateWrapper);
    }

    @Test
    public void updateByWapperLambdaChain(){
        boolean update=new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getName,"大强").eq(User::getAge,31).set(User::getAge,32).update();
    }

}
