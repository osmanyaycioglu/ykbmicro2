package com.ykb.spring;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyAuthFilter extends ZuulFilter {

    private final Map<String, AccessToken> accessTokens = new HashMap<>();

    @Autowired
    private RestTemplate                   rt;


    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContextLoc = RequestContext.getCurrentContext();
        HttpServletRequest requestLoc = currentContextLoc.getRequest();
        String headerLoc = requestLoc.getHeader("Authorization");
        if (headerLoc == null) {
            throw new ZuulException("Lütfen username password gönderin",
                                    403,
                                    "abcd");
        }
        String trimLoc = headerLoc.substring("Basic".length())
                                  .trim();
        byte[] decodeLoc = Base64.getDecoder()
                                 .decode(trimLoc.getBytes());
        String userPass = new String(decodeLoc);

        AccessToken accessTokenFromCacheLoc = this.accessTokens.get(userPass);
        if (accessTokenFromCacheLoc == null) {

            String[] upPair = userPass.split(":");

            // get Token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = "client1" + ":" + "1234";
            byte[] encodedAuth = Base64.getEncoder()
                                       .encode(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.set("Authorization",
                        authHeader);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type",
                    "password");
            map.add("username",
                    upPair[0]);
            map.add("password",
                    upPair[1]);
            map.add("scope",
                    "xyz abc");

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map,
                                                                                headers);

            try {
                ResponseEntity<String> response = this.rt.exchange("http://localhost:7070/oauth/token",
                                                                   HttpMethod.POST,
                                                                   entity,
                                                                   String.class);
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectReader readerLoc = objectMapper.readerFor(AccessToken.class);
                AccessToken accessTokenLoc = readerLoc.readValue(response.getBody());
                String access_tokenLoc = accessTokenLoc.getAccess_token();
                String token_typeLoc = accessTokenLoc.getToken_type();
                currentContextLoc.addZuulRequestHeader("Authorization",
                                                       token_typeLoc + " " + access_tokenLoc);
                this.accessTokens.put(userPass,
                                      accessTokenLoc);
            } catch (Exception e) {
                throw new ZuulException(e.getMessage(),
                                        400,
                                        "problem while getting credential");
            }
        } else {
            currentContextLoc.addZuulRequestHeader("Authorization",
                                                   accessTokenFromCacheLoc.getToken_type()
                                                                    + " "
                                                                    + accessTokenFromCacheLoc.getAccess_token());

        }
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

}
