package nxl.mp.springboot;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nxl.mp.springboot.mapper.UserMapper;
import nxl.mp.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){

        User user=new User();
        user.setMail("2@itcase.cn");
        user.setAge(32);
        user.setUserName("caocao");
        user.setName("曹操1");
        user.setPassword("123456");

        int result = this.userMapper.insert(user);  //result表示数据库受影响的行数

        System.out.println("result => "+result);

        //获取自增长后的id值,自增长后的id值会回填到user对象中

        System.out.println("id=>"+user.getId());

    }

    @Test
    public void testSelectById(){
        User user = this.userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testUpdateById(){
        User user =new User();
        user.setId(1L); // 1L 为  条件，根据id更新
        user.setAge(19); //更新的字段
        user.setPassword("6666666");

        int result = this.userMapper.updateById(user);
        System.out.println("result"+result);
    }

    @Test
    public void testUpdate(){
        User user =new User();
        user.setAge(20); //更新的字段
        user.setPassword("888888");

        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //这就是条件  ，匹配user_name= zhangsan 的用户数据
        wrapper.eq("user_name","zhangsan");
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result=>"+result);
    }

    @Test
    public void testUpdate2(){
        //更新的条件以及字段
        UpdateWrapper<User> wrapper=new UpdateWrapper<>();

        //数据库中的字段名字而不是属性名
        wrapper.set("age",21).set("password","999999")  //更新的字段
                .eq("user_name","zhangsan");  //更新的条件

        //根据条件做更新

        int reuslt=this.userMapper.update(null,wrapper);
        System.out.println("result=>"+reuslt);

    }

    @Test
    public void testDeleteById(){

        //根据id删除数据
        int result = this.userMapper.deleteById(7L);

        System.out.println("result=>"+result);
    }

    @Test
    public void testDeleteByMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("user_name","zhangsan");
        map.put("password","999999");

        //根据map删除数据，多条件之间是and关系
       int result=  this.userMapper.deleteByMap(map);

        System.out.println("result=>"+result);

    }

    @Test
    public void testDelete(){
        //第一种方式
     // QueryWrapper<User> wrapper=new QueryWrapper<>();
     // wrapper.eq("user_name","caocao1").eq("password","123456");


        //第二种方式
        User user=new User();
        user.setUserName("123456");
        user.setPassword("caocao");
        QueryWrapper<User> wrapper=new QueryWrapper<>(user);
       //根据包装条件做删除
       int result=  this.userMapper.delete(wrapper);
        System.out.println("result =>"+result);

    }

    @Test
    public void testDeleteBatchIds(){
        //根据id批量删除数据
        int result=this.userMapper.deleteBatchIds(Arrays.asList(3L,4L));
        System.out.println("result=>"+result);
    }

    @Test
    public void testSelectBatchIds(){
        //根据id进行批量查询
        List<User> users= this.userMapper.selectBatchIds(Arrays.asList(2L,3L,4L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //查询条件
        wrapper.eq("user_name","lisi");

        //查询的数据超过一条时，会抛出异常
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount(){
       QueryWrapper<User> wrapper=new QueryWrapper<>() ;
        wrapper.gt("age",20); //条件：年龄大于20岁的用户
       //根据条件查询数据条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count => "+count);

    }

    @Test
    public void testSelectList(){

        QueryWrapper<User> wrapper=new QueryWrapper<>();

        //设置查询条件
        wrapper.like("email","itcast");
        List<User> users = this.userMapper.selectList(wrapper);

        for (User user : users) {
            System.out.println(user);
        }
    }
     //测试分页查询
    @Test
     public void testSelectPage(){

        Page<User> page=new Page<>(1,1);  //查询第一页，查询1条数据
        
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        
        //设置查询条件
        
        wrapper.like("email","itcast");
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);

        System.out.println("数据总条数"+iPage.getTotal());
        System.out.println("数据总页数"+iPage.getPages());
        System.out.println("当前页数"+iPage.getCurrent());

        //数据集合
        List<User> records = iPage.getRecords();

        for (User record : records) {
            System.out.println(record);
        }
    }
}
