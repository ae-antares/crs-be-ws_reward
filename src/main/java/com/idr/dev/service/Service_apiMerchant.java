package com.idr.dev.service;


import com.idr.dev.mapper.Mapper_MCMerchant;
import com.idr.dev.model.Data_InfoResponse;
import com.idr.dev.model.In_MsgMerchant;
import com.idr.dev.model.Out_MsgMerchant;
import com.idr.dev.model.MC_Merchant;

import com.idr.dev.model.ess_FILTER;
import com.idr.dev.model.ess_PAGINATION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.soap.SOAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Devon
 */
@Component
@Path("merchant")
@Service("serviceMerchant")
public class Service_apiMerchant {
    @Autowired private Mapper_MCMerchant mapperMerchant;
//    @Autowired private Service_Essential serviceEssential;

    private static final int RES_OK_NO_CREATE = 200;
    private static final int RES_SUCCESS_CREATE = 201;
    private static final int RES_BAD_REQUEST = 400;
    private static final int RES_NOT_FOUND = 404;
    private static final int RES_CONFLICT = 409;

    @POST @Path("/fetch")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response fetch_MERCHANT(In_MsgMerchant msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();

        // -- Logic Filter Data
        MC_Merchant data = msgIN.getFILTER();
        List<String> filters = new ArrayList<>();
            if(data.getMID()!= null) filters.add("upper(mct.MID) LIKE uppe\"upper(mctr('%"+data.getMID()+"%')");
            if(data.getMERCHANT_NAME()!= null) filters.add("upper(mct.MERCHANT_NAME) LIKE upper('%"+data.getMERCHANT_NAME()+"%')");
            if(data.getINDUSTRY_AREA()!= null) filters.add("upper(mct.INDUSTRY_AREA) LIKE upper('%"+data.getINDUSTRY_AREA()+"%')");
            if(data.getBANK_ACCOUNT_NUMBER()!= null) filters.add("upper(mct.LIFESPAN_MODE) LIKE upper('%"+data.getBANK_ACCOUNT_NUMBER()+"%')");
            if(data.getREF_ID()!= null) filters.add("upper(mct.REF_ID) LIKE upper('%"+data.getREF_ID()+"%')");
        ess_FILTER query = new ess_FILTER(filters); query.setPAGE(msgIN.getPAGINATION());
        // -- END Logic Filter Data

        // --  Logic load data with mapper
        List<MC_Merchant> datas = mapperMerchant.read_Merchant(query);
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

    int res = RES_BAD_REQUEST; Out_MsgMerchant out = null; 
    Data_InfoResponse mj = new Data_InfoResponse("00", "Load all Merchant masterdata records success!", true);
    out = new Out_MsgMerchant(datas, mj, page);
    res =  RES_OK_NO_CREATE;

    long elapsedTimeMillis = System.currentTimeMillis() - start;
    System.out.println("Speed = " + elapsedTimeMillis + "ms");

    return Response.status(res).entity(out).build();
    }

    @Transactional
    @POST @Path("/{action}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response approve_REQUEST(@PathParam("action") String action, MC_Merchant msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        int res = RES_BAD_REQUEST; Out_MsgMerchant out = null; 
        try{
            switch(action){
                case "create":{mapperMerchant.create_Merchant(msgIN);}break;
                case "update":{mapperMerchant.update_Merchant(msgIN);}break;
                case "delete":{mapperMerchant.delete_Merchant(msgIN);}break;
                default:{}break;
            }         
            Data_InfoResponse mj = new Data_InfoResponse("00", "Merchant Code "+msgIN.getMID()+" has been "+action+"ed.", true);
            res = RES_OK_NO_CREATE; out = new Out_MsgMerchant(mj);
        }catch(Exception e){
            Data_InfoResponse mj = new Data_InfoResponse("01", "Action failed : "+e, true);
            res = RES_BAD_REQUEST; out = new Out_MsgMerchant(mj);
            System.err.println(e);
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");

        return Response.status(res).entity(out).build();
    }
}

