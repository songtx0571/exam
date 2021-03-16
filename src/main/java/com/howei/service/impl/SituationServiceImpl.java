package com.howei.service.impl;

import com.howei.mapper.Situationmapper;
import com.howei.service.SituationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SituationServiceImpl implements SituationService {
    @Autowired
    private Situationmapper situationmapper;

    public Integer selecttimes(String UserName) {
        return situationmapper.selecttimes(UserName);
    }

    public void updatetime(String UserName, Integer times) {
        situationmapper.updatetime(UserName, times);
    }

}
