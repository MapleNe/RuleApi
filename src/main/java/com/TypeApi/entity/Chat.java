package com.TypeApi.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * TypechoChat
 * @author buxia97 2023-01-10
 */
@Data
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  
     */
    private Integer id;

    /**
     * uid  创建者
     */
    private Integer uid;

    /**
     * toid  也是创建者（和上一个字段共同判断私聊）
     */
    private Integer toid;

    /**
     * created  创建时间
     */
    private Integer created;

    /**
     * lastTime  最后聊天时间
     */
    private Integer lastTime;

    /**
     * type  0是私聊，1是群聊
     */
    private Integer type;

    /**
     * 聊天室名称（群聊）
     */
    private String name;

    /**
     * 图片地址（群聊）
     */
    private String pic;

    /**
     * 屏蔽和全体禁言，存操作人id
     */
    private Integer ban;
}