package nxl.mp.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(type= IdType.AUTO)  //表示id自增长，而不是随机的
    private Long id;
    private String userName;

    @TableField(select = false)  //表示查询时不返回该字段的值
    private String password;
    private String name;
    private Integer age;
    @TableField(value = "email")  //指定表中的字段名
    private String mail;

    @TableField(exist=false)  //表示在数据库中是不存在的
    private String address;
}
