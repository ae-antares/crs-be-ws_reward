package com.idr.dev.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
public @Data class In_MsgReward implements Serializable {
    //0. MAIN 
    private RW_Reward FILTER;
    private ess_PAGINATION PAGINATION;
}