package com.idr.dev.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Out_MsgMembership")
public @Data class Out_MsgMembership{
    private MS_Membership DATA;
    private List<MS_Membership> DATAS;
    private ess_PAGINATION INFO_PAGE;
    private Data_InfoResponse INFO_RESPONSE;

    public Out_MsgMembership(){}
    public Out_MsgMembership(Data_InfoResponse INFO_RESPONSE) {this.INFO_RESPONSE = INFO_RESPONSE;}
    public Out_MsgMembership(MS_Membership DATA, Data_InfoResponse INFO_RESPONSE, ess_PAGINATION INFO_PAGE) {this.DATA = DATA; this.INFO_RESPONSE = INFO_RESPONSE; this.INFO_PAGE = INFO_PAGE;}
    public Out_MsgMembership(List<MS_Membership> DATAS, Data_InfoResponse INFO_RESPONSE, ess_PAGINATION INFO_PAGE) {this.DATAS = DATAS; this.INFO_RESPONSE = INFO_RESPONSE; this.INFO_PAGE = INFO_PAGE;}
}
