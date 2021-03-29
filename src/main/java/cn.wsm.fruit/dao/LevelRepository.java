package cn.wsm.fruit.dao;

import cn.wsm.fruit.pojo.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;

public interface LevelRepository extends JpaRepository<Level, Integer> {

    Level findLevelByLv(@Param("lv") Integer level);

    Level findLevelByUid(@Param("uid") String uId);

}
