package com.idr.dev.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
public @Data class In_MsgMerchantGroup implements Serializable {
    //0. MAIN 
    private MG_MerchantGroup FILTER;
    private ess_PAGINATION PAGINATION;
}