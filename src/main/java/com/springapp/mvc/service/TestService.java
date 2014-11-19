package com.springapp.mvc.service;

import com.springapp.mvc.dao.TestDao;
import com.springapp.mvc.domain.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by papadimos on 23/10/2014.
 */
public class TestService {

    private TestDao testDao;

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    public List<Song> getSongsList() {
        return testDao.getSongsList();
    }
}
