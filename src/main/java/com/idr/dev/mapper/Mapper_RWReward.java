package com.idr.dev.mapper;

import com.idr.dev.model.RW_Reward;
import com.idr.dev.model.ess_FILTER;
import java.util.List;

public interface Mapper_RWReward {
    //--- Reward Masterdata Management
    public List<RW_Reward> read_Reward(ess_FILTER FILTER);
    //===
    public void create_Reward(RW_Reward data);
    public int update_Reward(RW_Reward data);
    public int delete_Reward(RW_Reward data);
}
