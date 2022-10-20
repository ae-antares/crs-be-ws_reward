package com.idr.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.idr.dev.mapper.Mapper_mainSERVICE;
import com.idr.dev.model.In_MsgJSON;
import com.idr.dev.model.Out_MsgJSON;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.io.IOException;
import javax.xml.bind.JAXBElement;
import javax.xml.soap.SOAPException;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@Path("main")
@Service("serviceMAIN")
public class Service_apiMain {
    @Autowired private Mapper_mainSERVICE mapperMAIN;
    
    private static final int RES_OK_NO_CREATE = 200;
    private static final int RES_SUCCESS_CREATE = 201;
    private static final int RES_BAD_REQUEST = 400;
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @POST @Path("/echo")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response echo_PGVERSION(In_MsgJSON msgIN) throws SOAPException, IOException{
        long start = System.currentTimeMillis();
        String resVALIDATON = "00";
        
        String name = "admin";
        String password = "admin";
        String authString = name + ":" + password;
        
        String encryptedValue = new String(Base64.getEncoder().encode(authString.getBytes()));
        System.out.println(encryptedValue);
        
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");

        Out_MsgJSON mj = new Out_MsgJSON(resVALIDATON, resVALIDATON, mapperMAIN.echo_DBVERSION(msgIN));
        return Response.status(RES_BAD_REQUEST).entity(mj).build();
    }
    
    @POST @Path("/wsdl")
    @Consumes({MediaType.TEXT_XML})
    @Produces({MediaType.TEXT_XML})
    public Response WSDL(JAXBElement<In_MsgJSON> msgIN){
        long start = System.currentTimeMillis();
        String resVALIDATON = "00";
        
        String name = "admin";
        String password = "admin";
        String authString = name + ":" + password;
        
        String encryptedValue = new String(Base64.getEncoder().encode(authString.getBytes()));
        System.out.println(encryptedValue);
        
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Speed = " + elapsedTimeMillis + "ms");

        Out_MsgJSON mj = new Out_MsgJSON(resVALIDATON, resVALIDATON, "Transaction Declined!");
        return Response.status(RES_BAD_REQUEST).entity(mj).build();
    }
    
}
