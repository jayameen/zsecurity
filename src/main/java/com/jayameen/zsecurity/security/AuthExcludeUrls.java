package com.jayameen.zsecurity.security;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Madan KN
 */
@Component
public class AuthExcludeUrls {

    @Autowired private ResourceLoader resourceLoader;
    private List<String> excludeUrls = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> getExcludeUrls() {
        if(!isInitialized) { initialize(); }
        return excludeUrls;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static volatile boolean isInitialized = false;
    synchronized void initialize() {
        if (!isInitialized) {
            isInitialized = true;
            try {
                Resource resource = resourceLoader.getResource("classpath:auth_exclude_urls.txt");
                File file = resource.getFile();
                excludeUrls = FileUtils.readLines(file, "UTF-8");
            } catch (Exception e) {
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
