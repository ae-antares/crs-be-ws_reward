
package com.idr.dev.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Out_MsgMerchantGroup")
public class Out_MsgMerchantGroup {
    private MG_MerchantGroup DATA;
    private List<MG_MerchantGroup> DATAS;
    private ess_PAGINATION INFO_PAGE;
    private Data_InfoResponse INFO_RESPONSE;

    public Out_MsgMerchantGroup() {
    }

    public Out_MsgMerchantGroup(Data_InfoResponse INFO_RESPONSE) {
        this.INFO_RESPONSE = INFO_RESPONSE;
    }
    
    public Out_MsgMerchantGroup(List<MG_MerchantGroup> DATAS, ess_PAGINATION INFO_PAGE, Data_InfoResponse INFO_RESPONSE) {
        this.DATAS = DATAS;
        this.INFO_PAGE = INFO_PAGE;
        this.INFO_RESPONSE = INFO_RESPONSE;
    }

    public Out_MsgMerchantGroup(MG_MerchantGroup DATA, ess_PAGINATION INFO_PAGE, Data_InfoResponse INFO_RESPONSE) {
        this.DATA = DATA;
        this.INFO_PAGE = INFO_PAGE;
        this.INFO_RESPONSE = INFO_RESPONSE;
    }
    
}
