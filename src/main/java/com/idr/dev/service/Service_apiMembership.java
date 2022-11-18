package com.idr.dev.service;

import com.idr.dev.mapper.Mapper_MSMembership;
import com.idr.dev.mapper.Mapper_RWReward;
import com.idr.dev.model.Data_InfoResponse;
import com.idr.dev.model.In_MsgMembership;
import com.idr.dev.model.In_MsgReward;
import com.idr.dev.model.MS_Membership;
import com.idr.dev.model.Out_MsgMembership;
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
@Path("membership")
@Service("serviceMembership")
public class Service_apiMembership {
    @Autowired private Mapper_MSMembership mapperMembership;
//    @Autowired private Service_Essential serviceEssential;
    
    private static final int RES_OK_NO_CREATE = 200;
    private static final int RES_SUCCESS_CREATE = 201;
    private static final int RES_BAD_REQUEST = 400;
    private static final int RES_NOT_FOUND = 404;
    private static final int RES_CONFLICT = 409;
    
    @POST @Path("/fetch")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response fetch_MEMBERSHIP(In_MsgMembership msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        
        // -- Logic Filter Data
        MS_Membership data = msgIN.getFILTER();
        List<String> filters = new ArrayList<>();
            if(data.getMEMBERSHIP_CODE()!= null) filters.add("upper(mbs.MEMBERSHIP_CODE) LIKE upper('%"+data.getMEMBERSHIP_CODE()+"%')");
        ess_FILTER query = new ess_FILTER(filters); query.setPAGE(msgIN.getPAGINATION());
        // -- END Logic Filter Data
        
        // --  Logic load data with mapper
        List<MS_Membership> datas = mapperMembership.read_Membership(query);
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
        
        // -- Logic Create Response
        int res = RES_BAD_REQUEST; Out_MsgMembership out = null; 
        Data_InfoResponse mj = new Data_InfoResponse("00", "Load all Membership masterdata records success!", true);
        out = new Out_MsgMembership(datas, mj, page);
        res =  RES_OK_NO_CREATE;
        // -- END Logic Create Response
        
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");
        
        return Response.status(res).entity(out).build();
    }
}
