package orz.xuchao.learnmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import orz.xuchao.learnmybatisplus.dao.UserDao;
import orz.xuchao.learnmybatisplus.entity.User;
import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {

    @Autowired
    private UserDao userMapper;

    @Test
    public void deleteById(){
        userMapper.deleteById(2);
    }

    @Test
    public void deleteByMap(){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age", "10");
        userMapper.deleteByMap(columnMap);
    }

    @Test
    public void deleteByBatchIds(){
        userMapper.deleteBatchIds(Arrays.asList(1182105164730793987L,1182105164730793986L));
    }

    @Test
    public void deleteByWrapper(){
        LambdaQueryWrapper<User> lambdaQuery= Wrappers.<User>lambdaQuery();
        lambdaQuery.eq(User::getAge,13).or().gt(User::getAge,41);
        userMapper.delete(lambdaQuery);
    }


//    测试逻辑删除的，注意bean和yml文件里面的logic配置
    @Test
    public void deleteByIdLogic(){
        int rows=userMapper.deleteById(1184400792827600897L);
        System.out.println(rows);

    }
    @Test
    public void selectLogic(){
        List<User> list=userMapper.selectList(null);
        list.forEach(System.out::println);

    }

}
