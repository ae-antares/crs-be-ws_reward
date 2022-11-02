package com.idr.dev.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
public @Data class In_MsgMembership implements Serializable {
    //0. MAIN 
    private MS_Membership FILTER;
    private ess_PAGINATION PAGINATION;
}