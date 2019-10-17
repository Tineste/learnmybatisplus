package orz.xuchao.learnmybatisplus.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

//        可以通过判断o是否为空来确定是否执行自动填充还是用自己的设置的
        Object o=getFieldValByName("createTime",metaObject);

        boolean hasSetter1=metaObject.hasSetter("createTime");
        if(hasSetter1)
            setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
