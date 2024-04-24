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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"activeOnSunday","activeOnMonday","activeOnTuesday","activeOnWednesday","activeOnThursday","activeOnFriday","activeOnSaturday","startTime","endTime"})
public class PromotionActiveDays {
    @JsonProperty(value = "ActiveOnSunday")
    private String ActiveOnSunday = "true";
    @JsonProperty(value = "ActiveOnMonday")
    private String ActiveOnMonday = "true";
    @JsonProperty(value = "ActiveOnTuesday")
    private String ActiveOnTuesday = "true";
    @JsonProperty(value = "ActiveOnWednesday")
    private String ActiveOnWednesday = "true";
    @JsonProperty(value = "ActiveOnThursday")
    private String ActiveOnThursday = "true";
    @JsonProperty(value = "ActiveOnFriday")
    private String ActiveOnFriday = "true";
    @JsonProperty(value = "ActiveOnSaturday")
    private String ActiveOnSaturday = "true";
    @JsonProperty(value = "StartTime")
    private String StartTime = "00:00:00";
    @JsonProperty(value = "EndTime")
    private String EndTime = "23:59:00";
}
