/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eu.evaluation.model.eva.history;

import com.eu.evaluation.model.NameEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "t_res_version")
public class EvaluateVersion extends NameEntity {

    private static final long serialVersionUID = 5022835329486903096L;

    public final static int STATUS_NOT_START = 0;//未开始

    public final static int STATUS_COPYED_EVALUATE = 1;//已复制评测项目

    public final static int STATUS_READY = 2;//已准备好评测数据

    public final static int STATUS_EVALUATING = 4;//评测中

    public final static int STATUS_FINISHED = 5;//评测完成

    private int status = 0;//状态

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
