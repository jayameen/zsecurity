package com.jayameen.zsecurity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Madan KN
 */

@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class AppResponse<T> implements Serializable {

    @JsonProperty("status")
    private String status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("records_total")
    private Long recordsTotal;

    @JsonProperty("records_filtered")
    private Long recordsFiltered;

    @JsonProperty("pages_total")
    private Long pagesTotal;

    @JsonProperty("draw")
    private Long draw;

    @JsonProperty("data")
    private List<T> data;

}
