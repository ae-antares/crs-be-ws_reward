/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.idr.dev.mapper;

import com.idr.dev.model.MC_Merchant;
import com.idr.dev.model.ess_FILTER;
import java.util.List;

/**
 *
 * @author Devon
 */
public interface Mapper_MCMerchant {
    //--- Merchant Masterdata Management
    public List<MC_Merchant> read_Merchant(ess_FILTER FILTER);
    //===
    public void create_Merchant(MC_Merchant data);
    public int update_Merchant(MC_Merchant data);
    public int delete_Merchant(MC_Merchant data);
}
