package orz.xuchao.learnmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import orz.xuchao.learnmybatisplus.dao.UserDao;
import orz.xuchao.learnmybatisplus.entity.User;
import orz.xuchao.learnmybatisplus.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
