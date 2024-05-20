package com.jayameen.zsecurity.security;

import com.jayameen.zsecurity.BaseController;
import com.jayameen.zsecurity.dto.AppResponse;
import com.jayameen.zsecurity.dto.ZToken;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Madan KN
 */
@Controller
@RequiredArgsConstructor
public class AuthenticationController extends BaseController {

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}") protected String tokenServiceAPIUrl;
    @Value("${spring.security.oauth2.client.registration.zsecurity.client-secret}") protected String clientSecret;
    @Value("${spring.security.oauth2.client.registration.zsecurity.client-id}") protected String clientID;
    @Value("${spring.security.oauth2.client.registration.zsecurity.redirect-uri}") protected String redirectUri;
    @Value("${app.key-cloak-logout-url}") protected String logoutUrl;
    @Value("${spring.security.oauth2.client.provider.keycloak.authorization-uri}") protected String authorizationUri;

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/login/authcode", method = RequestMethod.GET)
    public ModelAndView loginAuthCode(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "code", required = false, defaultValue = "") String code) {
        ModelAndView mv = new ModelAndView("index");
        if(StringUtils.isNotBlank(code)){
            ZToken tokens = fetchTokens(code);
            mv.addObject("access_token", tokens.getAccessToken());
            mv.addObject("refresh_token", tokens.getRefreshToken());
        }
        return mv;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/api/login/authcode", method = RequestMethod.POST)
    public ResponseEntity<AppResponse> apiloginAuthCode(@RequestParam(value = "code", required = true, defaultValue = "") String code) {
        AppResponse appResponse = new AppResponse<>();
        if(StringUtils.isNotBlank(code)){
            handleObjectSuccess(appResponse, fetchTokens(code));
        }
        return ResponseEntity.ok(appResponse);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/api/refresh-token", method = RequestMethod.POST)
    public ResponseEntity<AppResponse> loginRefreshToken(@RequestBody ZToken tokensRequest) {
       AppResponse appResponse = new AppResponse<>();
       if(StringUtils.isNotBlank(tokensRequest.getRefreshToken())){
           ZToken tokenResponse = refreshToken(tokensRequest.getRefreshToken());
           handleObjectSuccess(appResponse, tokenResponse);
       }
       return ResponseEntity.ok(appResponse);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String loginUrl = authorizationUri+"?client_id="+clientID+"&redirect_uri="+redirectUri+"&response_type=code";
        return "redirect:"+loginUrl;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<AppResponse> loginUsingAPI(@RequestParam(value = "username", required = true, defaultValue = "") String username,
                                                     @RequestParam(value = "password", required = true, defaultValue = "") String password) {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, loginWithUserNameAndPassword(username, password ));
        return ResponseEntity.ok(appResponse);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(path="/api/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<AppResponse> logout(@RequestBody ZToken token) throws Exception {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, logoutToken(token));
        return ResponseEntity.ok(appResponse);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ZToken fetchTokens(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("client_id", clientID);
        map.add("redirect_uri", redirectUri);
        map.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForEntity( tokenServiceAPIUrl, requestEntity,  ZToken.class ).getBody();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ZToken refreshToken(String refreshToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("client_id", clientID);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", refreshToken);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForEntity( tokenServiceAPIUrl, requestEntity , ZToken.class ).getBody();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String logoutToken(ZToken token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(token.getAccessToken());
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("client_id", clientID);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", token.getRefreshToken());
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForEntity( logoutUrl, requestEntity , String.class ).getBody();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    private ZToken loginWithUserNameAndPassword(String userName, String passWord){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", userName);
        map.add("password", passWord);
        map.add("grant_type", "password");
        map.add("client_id", clientID);
        map.add("redirect_uri", redirectUri);
        map.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForEntity( tokenServiceAPIUrl, requestEntity,  ZToken.class ).getBody();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
}
