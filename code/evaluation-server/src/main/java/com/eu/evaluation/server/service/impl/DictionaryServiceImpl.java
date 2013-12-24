/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service.impl;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;
import com.eu.evaluation.server.dao.dictionary.FieldDictionaryDAO;
import com.eu.evaluation.server.dao.dictionary.ObjectDictionaryDAO;
import com.eu.evaluation.server.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class DictionaryServiceImpl implements DictionaryService{
    
    @Autowired
    private ObjectDictionaryDAO objectDictionaryDAO;
    
    @Autowired
    private FieldDictionaryDAO fieldDictionaryDAO;

    public ObjectDictionary findObjectDictionary(String id) {
        return objectDictionaryDAO.get(id);
    }

    public FieldDictionary findFielddDictionary(String id) {
        return fieldDictionaryDAO.get(id);
    }
    
}
