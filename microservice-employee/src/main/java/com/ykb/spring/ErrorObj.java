package com.ykb.spring;

import java.util.ArrayList;
import java.util.List;

public class ErrorObj {

    private String         errorDesc;
    private int            errorCause;
    private List<ErrorObj> errorObjs;


    public ErrorObj() {
    }

    public ErrorObj(final String errorDescParam,
                    final int errorCauseParam) {
        super();
        this.errorDesc = errorDescParam;
        this.errorCause = errorCauseParam;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(final String errorDescParam) {
        this.errorDesc = errorDescParam;
    }

    public int getErrorCause() {
        return this.errorCause;
    }

    public void setErrorCause(final int errorCauseParam) {
        this.errorCause = errorCauseParam;
    }

    public void addError(final ErrorObj err) {
        if (this.errorObjs == null) {
            this.errorObjs = new ArrayList<>();
        }
        this.getErrorObjs()
            .add(err);
    }

    public List<ErrorObj> getErrorObjs() {
        return this.errorObjs;
    }

    public void setErrorObjs(final List<ErrorObj> errorObjsParam) {
        this.errorObjs = errorObjsParam;
    }
}
