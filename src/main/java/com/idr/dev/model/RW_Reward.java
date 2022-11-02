package com.idr.dev.model;

import java.io.Serializable;
import lombok.Data;

public @Data class RW_Reward implements Serializable {
    //0. MAIN 
    private Long ID;
    private String REWARD_CODE;
    private String REWARD_NAME;    
    private String REWARD_DESC;
    
    private String VALID_STARTDATE;
    private String VALID_ENDDATE;
    
    private String REWARD_OUTPUT;
    private String LIFESPAN_MODE;
    private String LIFESPAN_MODE_INFO;
    
    private ess_META META;
    
    private Boolean IS_DUMP;
    private Boolean ACTIVE_STATUS;
}