package com.TypeApi.service.impl;

import com.TypeApi.entity.*;
import com.TypeApi.dao.*;
import com.TypeApi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现类
 * TypechoApiconfigServiceImpl
 * @author apiconfig
 * @date 2022/04/28
 */
@Service
public class ApiconfigServiceImpl implements ApiconfigService {

    @Autowired
    ApiconfigDao dao;

    @Override
    public int insert(Apiconfig apiconfig) {
        return dao.insert(apiconfig);
    }


    @Override
    public int update(Apiconfig apiconfig) {
    	return dao.update(apiconfig);
    }


	@Override
	public Apiconfig selectByKey(Object key) {
		return dao.selectByKey(key);
	}

}