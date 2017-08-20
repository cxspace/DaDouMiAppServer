package com.cxspace.ssm.service;

import com.cxspace.ssm.model.Share;

/**
 * Created by liujie on 2017/8/19.
 */
public interface ShareService extends BaseService<Share> {


    public void inc_support(String id);

}
