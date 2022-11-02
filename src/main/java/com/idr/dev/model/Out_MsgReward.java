package com.idr.dev.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Out_MsgRequest")
public @Data class Out_MsgReward{
    private RW_Reward DATA;
    private List<RW_Reward> DATAS;
    private ess_PAGINATION INFO_PAGE;
    private Data_InfoResponse INFO_RESPONSE;

    public Out_MsgReward(){}
    public Out_MsgReward(Data_InfoResponse INFO_RESPONSE) {this.INFO_RESPONSE = INFO_RESPONSE;}
    public Out_MsgReward(RW_Reward DATA, Data_InfoResponse INFO_RESPONSE, ess_PAGINATION INFO_PAGE) {this.DATA = DATA; this.INFO_RESPONSE = INFO_RESPONSE; this.INFO_PAGE = INFO_PAGE;}
    public Out_MsgReward(List<RW_Reward> DATAS, Data_InfoResponse INFO_RESPONSE, ess_PAGINATION INFO_PAGE) {this.DATAS = DATAS; this.INFO_RESPONSE = INFO_RESPONSE; this.INFO_PAGE = INFO_PAGE;}
}
