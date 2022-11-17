 package com.idr.dev.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

public @Data class MS_Membership implements Serializable {
    //0. MAIN 
    private Long ID;
    private String MEMBERSHIP_CODE;
    private String MEMBERSHIP_NAME;    
    private String MEMBERSHIP_DESC;
    private Boolean IS_REGISTERED;
    
    private Boolean IS_DUMP;
    private Boolean ACTIVE_STATUS;
    
    private ess_IMAGE IMAGE;
    private ess_META META;
    
    private List<ess_IMAGE> IMAGES;
}