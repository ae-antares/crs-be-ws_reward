package com.idr.dev.service;

import com.idr.dev.mapper.Mapper_MGMerchantGroup;
import com.idr.dev.model.Data_InfoResponse;
import com.idr.dev.model.In_MsgMerchantGroup;
import com.idr.dev.model.MG_MerchantGroup;
import com.idr.dev.model.Out_MsgMerchantGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
@Path("merchantgroup")
@Service("serviceMerchantGroup")
public class Service_apiMerchantGroup {
    @Autowired private Mapper_MGMerchantGroup mapperMerchantGroup;
    @Autowired private Service_Essential serviceEssential;
    
    private static final int RES_OK_NO_CREATE = 200;
    private static final int RES_SUCCESS_CREATE = 201;
    private static final int RES_BAD_REQUEST = 400;
    private static final int RES_NOT_FOUND = 404;
    private static final int RES_CONFLICT = 409;
    
    @POST @Path("/fetch")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response fetch_MERCHANT_GROUP(In_MsgMerchantGroup msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        
        // -- Logic Filter Data
        MG_MerchantGroup data = msgIN.getFILTER();
        List<String> filters = new ArrayList<>();
            if(data.getGROUP_CODE()!= null) filters.add("upper(mbs.GROUP_CODE) LIKE upper('%"+data.getGROUP_CODE()+"%')");
        ess_FILTER query = new ess_FILTER(filters); query.setPAGE(msgIN.getPAGINATION());
        // -- END Logic Filter Data
        
        // --  Logic load data with mapper
        List<MG_MerchantGroup> datas = mapperMerchantGroup.read_MerchantGroup(query);
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
        int res = RES_BAD_REQUEST; Out_MsgMerchantGroup out = null; 
        Data_InfoResponse mj = new Data_InfoResponse("00", "Load all Membership masterdata records success!", true);
        out = new Out_MsgMerchantGroup(datas, page, mj);
        res =  RES_OK_NO_CREATE;
        // -- END Logic Create Response
        
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");
        
        return Response.status(res).entity(out).build();
    }
    
    @Transactional
    @POST @Path("/{action}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response approve_REQUEST(@PathParam("action") String action, MG_MerchantGroup msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        int res = RES_BAD_REQUEST; Out_MsgMerchantGroup out = null; 
        try{
            switch(action){
                case "create":{mapperMerchantGroup.create_MerchantGroup(msgIN);}break;
                case "update":{mapperMerchantGroup.update_MerchantGroup(msgIN);}break;
                case "delete":{mapperMerchantGroup.delete_MerchantGroup(msgIN);}break;
                default:{}break;
            }         
            Data_InfoResponse mj = new Data_InfoResponse("00", "Group Code "+msgIN.getGROUP_CODE()+" has been "+action+"ed.", true);
            res = RES_OK_NO_CREATE; out = new Out_MsgMerchantGroup(mj);
        }catch(Exception e){
            Data_InfoResponse mj = new Data_InfoResponse("01", "Action failed : "+e, true);
            res = RES_BAD_REQUEST; out = new Out_MsgMerchantGroup(mj);
            System.err.println(e);
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");
        
        return Response.status(res).entity(out).build();
    }
}
