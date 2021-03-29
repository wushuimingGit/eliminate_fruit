package cn.wsm.fruit.pojo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "level")
@Entity
@Data
public class Level implements Serializable {


    // 关卡表的主键 也是做为 关卡的级别字段
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  one;
    private String  two;
    private String  three;
    private String  four;
    private String  five;
    private String  six;
    private String  seven;
    private String  eight;

    private Integer init;
    private String uid;
    private Integer lv;


    public Level getNewLevel(String uId){
        Level level = new Level();
        level.setOne(one);
        level.setTwo(two);
        level.setThree(three);
        level.setFour(four);
        level.setFive(five);
        level.setSix(six);
        level.setSeven(seven);
        level.setEight(eight);
        level.setLv(null);
        level.setInit(0);
        level.setUid(uId);
        return level;

    }


}
