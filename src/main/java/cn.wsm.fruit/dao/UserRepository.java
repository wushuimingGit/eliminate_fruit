package cn.wsm.fruit.dao;

import cn.wsm.fruit.pojo.Level;
import cn.wsm.fruit.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
