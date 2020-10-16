package io.swagger.api.constants;

public enum ApiErrorCodes {
    ERROR_EXIST(1,"Requested entity already exists"),
    ERROR_NOT_FOUND(2,"Requested entity does not exists");
    ;
    int code;
    String info;
    ApiErrorCodes(int code ,String info){
        this.code = code;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }
}
