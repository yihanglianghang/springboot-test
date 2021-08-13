package nxl.mp.springboot;

import nxl.mp.springboot.mapper.UserMapper;
import nxl.mp.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class  MpApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> users =
                this.userMapper.selectList(null);

        for (User user : users) {
            System.out.println(user);
        }

    }

}
