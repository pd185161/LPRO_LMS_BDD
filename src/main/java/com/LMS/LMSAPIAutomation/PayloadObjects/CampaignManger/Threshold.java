package com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"bucketId","thresholdTypeId","thresholdValue","thresholdSubTypeId"})
public class Threshold {
    @JsonProperty(value = "BucketId")
    private String BucketId;
    @JsonProperty(value = "ThresholdTypeId")
    private String ThresholdTypeId;
    @JsonProperty(value = "ThresholdValue")
    private String ThresholdValue;
    @JsonProperty(value = "ThresholdSubTypeId")
    private String ThresholdSubTypeId;
}
