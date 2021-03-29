package cn.wsm.fruit.service;

import cn.wsm.fruit.pojo.Level;

public interface LevelService {

    Level getLevel(Integer level);

    void addLevelForUser(Level level);

    Level getLevelByUid(String uId);


    Level move(Level level,Integer row0,Integer col0,Integer row1,Integer col1);


}
