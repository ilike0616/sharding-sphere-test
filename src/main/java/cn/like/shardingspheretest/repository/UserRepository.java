package cn.like.shardingspheretest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.like.shardingspheretest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByBirthday(Integer birthday);
}
