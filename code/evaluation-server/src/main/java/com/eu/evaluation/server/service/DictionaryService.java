/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.server.service;

import com.eu.evaluation.model.dictionary.FieldDictionary;
import com.eu.evaluation.model.dictionary.ObjectDictionary;

/**
 *
 * @author dell
 */
public interface DictionaryService {
    
    public ObjectDictionary findObjectDictionary(String id);
    
    public FieldDictionary findFielddDictionary(String id);
}
