package com.idr.dev.mapper;

import com.idr.dev.model.MG_MerchantGroup;
import com.idr.dev.model.ess_FILTER;
import java.util.List;

public interface Mapper_MGMerchantGroup {
    //--- MerchantGroup Masterdata Management
    public List<MG_MerchantGroup> read_MerchantGroup(ess_FILTER FILTER);
    
    public void create_MerchantGroup(MG_MerchantGroup data);
    public int update_MerchantGroup(MG_MerchantGroup data);
    public int delete_MerchantGroup(MG_MerchantGroup data);
}
