package orz.xuchao.learnmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//通过此注解指定类和表的对应关系，要是不指定则类名字和表名需要一致（User类对应user表）
@TableName("user")
//AR模式，一个对象对应一个类，对象需要继承 Model,加个serialVersionUID属性，然后一个mapper接口继承BaseMapper
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

//    如果表的主键就叫id可以不加此注解，要是不是叫id叫别的则主键要加这个注解
    @TableId
    private Long id;

//    当作为对象参数查询的时候加了此注解，条件都会变成Like
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    private Integer age;
    private String email;
//    如果表明和类属性名字一样，或是表名是manager_Id属性名是managerId，则不需要加注解，不然需要加此注解标明对应关系
    @TableField("manager_Id")
    private Long managerId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;




//    此注解是忽略此属性，对象里面有可能会用，但是数据库表里面没有这个字段
    @TableField(exist = false)
    private String remark;

//    读取数据的时候不读取这个字段，但是还是作为筛选条件的
    @TableField(value = "delete_flag",select = false,fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @Version
    @TableField(select = false)
    private int version;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", managerId=" + managerId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", version='" + version + '\'' +
                '}';
    }
}
