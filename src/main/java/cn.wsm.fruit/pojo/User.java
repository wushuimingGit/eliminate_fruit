package cn.wsm.fruit.pojo;


import lombok.Data;

import javax.persistence.*;


/**
 *  保留表 目前没有使用
 *
 */
@Table(name = "user")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uId;

    private Integer level;



}
