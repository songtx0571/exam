package com.howei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface SituationService {

	
    Integer selecttimes(String UserName);

    void updatetime(String UserName, Integer times);

}
