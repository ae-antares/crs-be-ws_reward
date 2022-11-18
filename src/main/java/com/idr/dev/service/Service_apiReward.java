package com.idr.dev.service;

import com.idr.dev.mapper.Mapper_RWReward;
import com.idr.dev.model.Data_InfoResponse;
import com.idr.dev.model.In_MsgReward;
import com.idr.dev.model.Out_MsgReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.idr.dev.model.RW_Reward;
import com.idr.dev.model.ess_FILTER;
import com.idr.dev.model.ess_PAGINATION;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.xml.soap.SOAPException;
import org.springframework.transaction.annotation.Transactional;

@Component
@Path("reward")
@Service("serviceReward")
public class Service_apiReward {
    @Autowired private Mapper_RWReward mapperReward;
    @Autowired private Service_Essential serviceEssential;
    
    private static final int RES_OK_NO_CREATE = 200;
    private static final int RES_SUCCESS_CREATE = 201;
    private static final int RES_BAD_REQUEST = 400;
    private static final int RES_NOT_FOUND = 404;
    private static final int RES_CONFLICT = 409;
    
    @POST @Path("/fetch")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response fetcn_REWARD(In_MsgReward msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        
        // -- Logic Filter Data
        RW_Reward data = msgIN.getFILTER();
        List<String> filters = new ArrayList<>();
            if(data.getREWARD_CODE()!= null) filters.add("upper(rwd.REWARD_CODE) LIKE uppe\"upper(rwdr('%"+data.getREWARD_CODE()+"%')");
            if(data.getREWARD_NAME()!= null) filters.add("upper(rwd.REWARD_NAME) LIKE upper('%"+data.getREWARD_NAME()+"%')");
            if(data.getREWARD_OUTPUT()!= null) filters.add("upper(rwd.REWARD_OUTPUT::varchar) LIKE upper('%"+data.getREWARD_OUTPUT()+"%')");
            if(data.getLIFESPAN_MODE()!= null) filters.add("upper(rwd.LIFESPAN_MODE::varchar) LIKE upper('%"+data.getLIFESPAN_MODE()+"%')");
            if(data.getLIFESPAN_MODE_INFO()!= null) filters.add("upper(rwd.LIFESPAN_MODE_INF) LIKE upper('%"+data.getLIFESPAN_MODE_INFO()+"%')");
        ess_FILTER query = new ess_FILTER(filters); query.setPAGE(msgIN.getPAGINATION());
        // -- END Logic Filter Data
        
        // --  Logic load data with mapper
        List<RW_Reward> datas = mapperReward.read_Reward(query);
        // --  END Logic load data with mapper
        
        // -- Logic Pagination
        ess_PAGINATION page = null;
        if(datas.size() > 0){
            Long total = datas.get(0).getMETA().getTOTAL_RECORD();
            page = msgIN.getPAGINATION();
                page.setTOTAL_RECORD(total);
                page.setPAGE_TOTAL((long) Math.ceil((double)total/(double)msgIN.getPAGINATION().getPAGE_MAX()));
        }
        // --  END Logic Pagination
        
        int res = RES_BAD_REQUEST; Out_MsgReward out = null; 
        Data_InfoResponse mj = new Data_InfoResponse("00", "Load all Reward masterdata records success!", true);
        out = new Out_MsgReward(datas, mj, page);
        res =  RES_OK_NO_CREATE;
        
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");
        
        return Response.status(res).entity(out).build();
    }
    
    @Transactional
    @POST @Path("/{action}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response approve_REQUEST(@PathParam("action") String action, RW_Reward msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        int res = RES_BAD_REQUEST; Out_MsgReward out = null; 
        try{
            switch(action){
                case "create":{mapperReward.create_Reward(msgIN);}break;
                case "update":{mapperReward.update_Reward(msgIN);}break;
                case "delete":{mapperReward.delete_Reward(msgIN);}break;
                default:{}break;
            }         
            Data_InfoResponse mj = new Data_InfoResponse("00", "Reward Code "+msgIN.getREWARD_CODE()+" has been "+action+"ed.", true);
            res = RES_OK_NO_CREATE; out = new Out_MsgReward(mj);
        }catch(Exception e){
            Data_InfoResponse mj = new Data_InfoResponse("01", "Action failed : "+e, true);
            res = RES_BAD_REQUEST; out = new Out_MsgReward(mj);
            System.err.println(e);
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");
        
        return Response.status(res).entity(out).build();
    }
}
