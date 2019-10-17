package orz.xuchao.learnmybatisplus.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import orz.xuchao.learnmybatisplus.entity.User;

import java.util.List;

public interface UserDao extends BaseMapper<User> {


//    @Select("SELECT * FROM user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);


    IPage<User> selectAll2(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
