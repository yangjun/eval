/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eu.evaluation.web.controller.pojo;

/**
 *
 * @author dell
 */
public class StatisticsVO {
    private String dimension;//维度
    
    private double score;//评分、百分比
    
    public StatisticsVO(String Dimension , double score){
        this.dimension = Dimension;
        this.score = score;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String Dimension) {
        this.dimension = Dimension;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
}
