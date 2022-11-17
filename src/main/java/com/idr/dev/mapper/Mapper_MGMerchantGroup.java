package com.idr.dev.mapper;

import com.idr.dev.model.MS_Membership;
import com.idr.dev.model.RW_Reward;
import com.idr.dev.model.ess_FILTER;
import java.util.List;

public interface Mapper_MSMembership {
    //--- Membership Masterdata Management
    public List<MS_Membership> read_Membership(ess_FILTER FILTER);
}
