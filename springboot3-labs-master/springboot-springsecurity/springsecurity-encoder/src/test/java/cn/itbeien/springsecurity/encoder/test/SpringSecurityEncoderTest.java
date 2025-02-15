package cn.itbeien.springsecurity.encoder.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest(classes = SpringSecurityEncoderTest.class)
public class SpringSecurityEncoderTest {
    @Test
    public void test() {
        //工作因子，默认值是10，最小值是4，最大值是31，值越大，加密越慢，安全程度越高
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        String result  = passwordEncoder.encode("123456");
        System.out.println(result);
        Assert.isTrue(passwordEncoder.matches("123456",result) , "密码错误");
    }
}
