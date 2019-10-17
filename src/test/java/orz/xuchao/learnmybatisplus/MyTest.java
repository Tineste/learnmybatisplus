package orz.xuchao.learnmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import orz.xuchao.learnmybatisplus.dao.UserDao;
import orz.xuchao.learnmybatisplus.entity.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private UserDao userMapper;
    @Test
    public void select() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
        System.out.println("===>" + list.get(0).getName());

    }


    @Test
    public void insert() {
        User user = new User();
        user.setName("朱六");
        user.setAge(10);
        user.setManagerId((long) 2);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById("1182090169351630850");
        System.out.println("===>" + user.toString());
    }

    @Test
    public void selectByIds() {
        List<Long> idsList = Arrays.asList(1182090169351630850L, 1182105164730793985L);
        List<User> userList = userMapper.selectBatchIds(idsList);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name","李雷");
        columnMap.put("age", "10");
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        姓名包含小，年龄20以下  name like "%小%" and age<20
//        queryWrapper.like("name","小").lt("age",20);

//        姓名包含小，年龄20到40之间，邮箱不为空 name like "%小%" and age between 20 and 40 and email is not null
//        queryWrapper.like("name","小").between("age",20,40).isNotNull("email");

//        名字为小姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
//        name like "小" or age>=40 Order by age desc，id asc
//        queryWrapper.likeRight("name","小").or().ge("age",20).orderByDesc("age").orderByAsc("id");


//        创建日期为2019年2月14日并且直属上级为性子为王姓
//        date_fromat(create_time,'%Y-%m-%d') and manager_id in(select id from user where name like '王%' )
//        queryWrapper.apply(" date_fromat(create_time,'%Y-%m-%d')={0}","2019-02-14")
//                .inSql("manager_id","select id from user where name like '王%'");

//        名字为王姓并且（年龄小于40或是邮箱不为空）
//        name like '王%' and (age<40 or email is not null)
//        queryWrapper.likeRight("name","王").and(wq->wq.lt("age",40).or().isNotNull("email"));


//        名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
//        name like '王%' or (age<40 and age>20 and email is not null)
//        queryWrapper.likeRight("name","王")
//                .or(wq->wq.lt("age",40).gt("age",20).isNotNull("email"));

//        (年龄小于40或邮箱不为空)并且名字为王姓
//        （age<40 or email is not null）and name like'王%'
//        queryWrapper.nested(wq->wq.lt("age",40).or().isNotNull("email"))
//                .likeRight("name","王");

//        年龄为30、31、34、35
//        age in（30、31、34、35）limit 2
//        queryWrapper.in("age",Arrays.asList(30,31,34,35)).last("limit 2");


//        姓名包含小，年龄20以下  name like "%小%" and age<20只要id和name
//        queryWrapper.select("id","name").like("name","小").lt("age",20);

//        姓名包含小，年龄20以下  name like "%小%" and age<20 不要id和name
//        queryWrapper.select(User.class,info -> !info.getColumn().equals("id")&&
//                !info.getColumn().equals("name")).like("name","小").lt("age",20);


//        按照多个或一个条件查询，看传入的是否为空的
//        String name="王";
//        String email="";
//        condition(name,email);


//        User whereUser=new User();
//        whereUser.setName("小明");
//        whereUser.setAge(10);
//        QueryWrapper<User> queryWrapper=new QueryWrapper<User>(whereUser);
//        如果后面再写上条件是添加上去的
//        queryWrapper.select("id","name").like("name","小").lt("age",20);


        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    public void condition(String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);

    }

    @Test
    public void seletOutAsMap() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id", "name").like("name", "小明").lt("age", 10);
        List<Map<String, Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectLambda() {
//        三种构造方法都行
//        LambdaQueryWrapper<User> lambda=new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<User>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
//        防止误写，name写成mame之类的
//        lambdaQuery.like(User::getName,"雨").lt(User::getAge,40);

//        lambdaQuery.likeRight(User::getName,"王").and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));


        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectLambda2() {

        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName, "雨").ge(User::getAge, 20).list();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectMy(){
        LambdaQueryWrapper<User> lambdaQuery=Wrappers.<User>lambdaQuery();
        lambdaQuery.likeRight(User::getName,"小")
                .and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
        List<User> userList=userMapper.selectAll(lambdaQuery);
        userList.forEach(System.out::println);

    }

    @Test
    public void selectByPages(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.ge("age",5);

//        不需要记录数的时候
//        Page<User> page=new Page<User>(1,2,false);

        Page<User> page=new Page<User>(1,2);

//        IPage<User> iPage=userMapper.selectPage(page,queryWrapper);


        IPage<Map<String,Object>> iPage=userMapper.selectMapsPage(page,queryWrapper);
        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
//        List<User> userList=iPage.getRecords();

        List<Map<String,Object>> userList=iPage.getRecords();
        userList.forEach(System.out::println);


    }

    @Test
    public void selectByPages2(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.ge("age",5);
//        不需要记录数的时候
        Page<User> page=new Page<User>(1,2,false);
//        Page<User> page=new Page<User>(1,2);
        IPage<User> iPage=userMapper.selectAll2(page,queryWrapper);
        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<User> userList=iPage.getRecords();
        userList.forEach(System.out::println);


    }


}
