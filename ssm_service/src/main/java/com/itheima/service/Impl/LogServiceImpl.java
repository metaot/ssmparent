package com.itheima.service.Impl;

import com.itheima.dao.LogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logDao;

    @Override
    public void save(SysLog sysLog) {
        logDao.save(sysLog);
    }
}
