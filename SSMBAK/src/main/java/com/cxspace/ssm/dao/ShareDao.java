package com.cxspace.ssm.dao;

import com.cxspace.ssm.model.Share;

/**
 * Created by liujie on 2017/8/19.
 */
public interface ShareDao extends BaseDao<Share> {

    /**
     * 点赞
     *
     */
    public void inc_support(String id);

}
