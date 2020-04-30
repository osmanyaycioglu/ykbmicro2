package com.ykb.spring;


public class AccessToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int    expires_in;
    private String scope;
    private String jti;
    private long   createTime = System.currentTimeMillis();

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(final String access_tokenParam) {
        this.access_token = access_tokenParam;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setToken_type(final String token_typeParam) {
        this.token_type = token_typeParam;
    }

    public String getRefresh_token() {
        return this.refresh_token;
    }

    public void setRefresh_token(final String refresh_tokenParam) {
        this.refresh_token = refresh_tokenParam;
    }

    public int getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(final int expires_inParam) {
        this.expires_in = expires_inParam;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(final String scopeParam) {
        this.scope = scopeParam;
    }

    public String getJti() {
        return this.jti;
    }

    public void setJti(final String jtiParam) {
        this.jti = jtiParam;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final long createTimeParam) {
        this.createTime = createTimeParam;
    }


}
