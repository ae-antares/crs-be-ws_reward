
package com.idr.dev.model;

import java.io.Serializable;
import lombok.Data;

public @Data class MG_MerchantGroup implements Serializable {
    private long ID;
    private String GROUP_CODE;
    private String GROUP_NAME;
    private String GROUP_DESC;
    
    private ess_META META;
    
    private Boolean IS_DUMP;
    private Boolean ACTIVE_STATUS;
}
