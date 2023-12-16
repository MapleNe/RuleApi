package com.TypeApi.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * TypechoComments
 * @author buxia97 2021-11-29
 */
@Data
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * coid  
     */
    private Integer coid;

    /**
     * cid  
     */
    private Integer cid;

    /**
     * created  
     */
    private Integer created;

    /**
     * author  
     */
    private String author;

    /**
     * authorId  
     */
    private Integer authorId;

    /**
     * ownerId  
     */
    private Integer ownerId;

    /**
     * mail  
     */
    private String mail;

    /**
     * url  
     */
    private String url;

    /**
     * ip  
     */
    private String ip;

    /**
     * agent  
     */
    private String agent;

    /**
     * text  
     */
    private String text;

    /**
     * type  
     */
    private String type;

    /**
     * status  
     */
    private String status;

    /**
     * parent  
     */
    private Integer parent;

    /**
     * likes
     */
    private Integer likes;

    /**
     * images
     */
    private String images;

    /**
     * opt
     */
    private String opt;

    /**
     * images
     */
    private Integer allparent;


}