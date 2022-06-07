package cn.like.shardingspheretest;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.like.shardingspheretest.entity.User;
import cn.like.shardingspheretest.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        IntStream.range(0, 20).forEach(i -> {
            User user = new User();
            //user.setId(System.nanoTime() + i);
            user.setUsername("user" + i);
            user.setBirthday((1998 + i) * 10000 + 100 + 2);
            userRepository.save(user);
        });
    }

    @Test
    void findOne() {
        List<User> user = userRepository.findAll();
        System.out.println("===>{}");
        userRepository.saveAll(user);
    }

}
