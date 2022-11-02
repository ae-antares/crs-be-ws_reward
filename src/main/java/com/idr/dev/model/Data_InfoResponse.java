package com.idr.dev.model;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement
public @Data class Data_InfoResponse{
    private String RESPONSE_CODE;
    private String ERROR_CODE;
    private String RESPONSE_MESSAGE;
    private String RESPONSE_TIMESTAMP;
    private String ENGINE_TYPE = "SERVICE-CLA-REQUEST";
    private String ENGINE;
    private String MENU_ACTION;
    private Long AFFECTED_DATA;
    
    private String ADDITIONAL_INFO;
    private String ACTION_BY;
    
    //Basic Constructor
    public Data_InfoResponse() {}

    //Constructor for Error Response
    public Data_InfoResponse(String RESPONSE_CODE, String ERROR_CODE, String RESPONSE_MESSAGE) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        
        this.RESPONSE_CODE = RESPONSE_CODE;
        this.ERROR_CODE = ERROR_CODE;
        this.RESPONSE_MESSAGE = RESPONSE_MESSAGE;
        this.RESPONSE_TIMESTAMP = ts.toString();
    }
    
    //Constructor for Success Response
    public Data_InfoResponse(String RESPONSE_CODE, String RESPONSE_MESSAGE, boolean dump) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        
        this.RESPONSE_CODE = RESPONSE_CODE;
        this.RESPONSE_MESSAGE = RESPONSE_MESSAGE;
        this.RESPONSE_TIMESTAMP = ts.toString();
    }
}
