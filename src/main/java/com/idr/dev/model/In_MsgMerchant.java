package com.idr.dev.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public @Data class In_MsgMerchant implements Serializable {
    //0. MAIN
    private MC_Merchant FILTER;
    private ess_PAGINATION PAGINATION;
}
