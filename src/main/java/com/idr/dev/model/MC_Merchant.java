package com.idr.dev.model;

import lombok.Data;

import java.io.Serializable;

public @Data class MC_Merchant implements Serializable {
    private Long ID;
    private String MID;
    private String MERCHANT_NAME;
    private String INDUSTRY_AREA;
    private String BANK_ACCOUNT_NUMBER;

    private String GROUP_INFO;
    private String REF_ID;
 
    private ess_META META;
    
    private Boolean IS_DUMP;
    private Boolean ACTIVE_STATUS;
}
