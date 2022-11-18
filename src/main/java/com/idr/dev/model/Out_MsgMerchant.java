/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.idr.dev.model;

import java.util.List;

/**
 *
 * @author Devon
 */
public class Out_MsgMerchant {
    private MC_Merchant DATA;
    private List<MC_Merchant> DATAS;
    private ess_PAGINATION INFO_PAGE;
    private Data_InfoResponse INFO_RESPONSE;

    public Out_MsgMerchant(){}
    public Out_MsgMerchant(Data_InfoResponse INFO_RESPONSE) {this.INFO_RESPONSE = INFO_RESPONSE;}
    public Out_MsgMerchant(MC_Merchant DATA, Data_InfoResponse INFO_RESPONSE, ess_PAGINATION INFO_PAGE) {this.DATA = DATA; this.INFO_RESPONSE = INFO_RESPONSE; this.INFO_PAGE = INFO_PAGE;}
    public Out_MsgMerchant(List<MC_Merchant> DATAS, Data_InfoResponse INFO_RESPONSE, ess_PAGINATION INFO_PAGE) {this.DATAS = DATAS; this.INFO_RESPONSE = INFO_RESPONSE; this.INFO_PAGE = INFO_PAGE;}
}
