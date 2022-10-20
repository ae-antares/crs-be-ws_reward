package com.idr.dev.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
public @Data class In_MsgJSON implements Serializable {
    //0. MAIN 
    private Long ID;
    private String AUTH_ID;
}