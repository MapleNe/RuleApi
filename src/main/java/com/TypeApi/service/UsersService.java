package com.TypeApi.service;

import java.util.List;
import com.TypeApi.entity.*;
import com.TypeApi.common.PageList;

/**
 * 业务层
 * TypechoUsersService
 * @author buxia97
 * @date 2021/11/29
 */
public interface UsersService {

    /**
     * [新增]
     **/
    int insert(Users users);

    /**
     * [批量新增]
     **/
    int batchInsert(List<Users> list);

    /**
     * [更新]
     **/
    int update(Users users);

    /**
     * [删除]
     **/
    int delete(Object key);

    /**
     * [批量删除]
     **/
    int batchDelete(List<Object> keys);

    /**
     * [主键查询]
     **/
    Users selectByKey(Object key);

    /**
     * [条件查询]
     **/
    List<Users> selectList (Users users);

    /**
     * [分页条件查询]
     **/
    PageList<Users> selectPage (Users users, Integer page, Integer pageSize, String searchKey, String order);

    /**
     * [总量查询]
     **/
    int total(Users users, String searchKey);
}
