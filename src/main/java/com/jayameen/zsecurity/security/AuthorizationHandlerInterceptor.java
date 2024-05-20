package com.jayameen.zsecurity.security;

/**
 * @author Madan KN
 */

import org.bson.BsonDocument;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Component
public class AuthorizationHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String     requestURI = request.getMethod() + ":" + request.getRequestURI();
            String          token = request.getHeader("Authorization").replaceAll("Bearer", "").trim();
            String[]        parts = token.split("\\.");
            BsonDocument document = new JsonObject(decode(parts[1])).toBsonDocument();
            String   scopeGranted = document.get("scope").toString().toLowerCase();
            String   realmRoles   = document.get("realm_access").asDocument().get("roles").toString().toLowerCase();
            //System.out.println(requestURI + " - " + scopeGranted + " - " + realmRoles + " - " + token);
            if(realmRoles.toLowerCase().contains("super_admin")){
              return true;
            }else if(scopeGranted.toLowerCase().contains(requestURI.toLowerCase())){
                return true;
            }else{
                response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                return false;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return false;
        }
    }

    private String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
