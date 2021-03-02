package com.example.models;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class Rewards {

    @JsonInclude 
    @Transient    
    protected Long points;

    public abstract Long getPoints();

}