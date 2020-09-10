package com.cwj.ssm.template.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cwj.ssm.template.framework.redis.ICache;

public abstract class BaseService {

    protected static final Logger log = LoggerFactory.getLogger(BaseService.class);
    
    @Autowired
    protected ICache iCache;
    
    /**
     * 验证必填属性
     * 如果Object instanceof String, 还需验证是否为空白字符串
     * @return {false} 含有NULL对象
     */
    protected boolean verifyRequire(Object... objs) {
        if (objs == null) return false;
        for (Object obj : objs) {
            if (obj == null || obj instanceof String && obj.toString().trim().length() == 0) return false;
        }
        return true;
    }
}
