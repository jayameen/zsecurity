package com.jayameen.zsecurity;

/**
 * @author Madan KN
 */

import com.jayameen.zsecurity.dto.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ViewFrontController extends BaseController{


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/api/products")
    public ResponseEntity<AppResponse> products() {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, "products list fetched successfully");
        return  ResponseEntity.ok().body(appResponse);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/error-401.html")
    public String e401() {
        return "error-401";
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

}
