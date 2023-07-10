package com.vorova.gifts.service.impl;

import com.vorova.gifts.dao.abstraction.ActionDao;
import com.vorova.gifts.model.entity.Action;
import com.vorova.gifts.service.abstraction.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

    private final ActionDao actionDao;

    @Autowired
    public ActionServiceImpl(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    @Override
    public void add(Action action) {
        actionDao.add(action);
    }
}
