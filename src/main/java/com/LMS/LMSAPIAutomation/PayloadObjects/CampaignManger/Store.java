package com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
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
@JsonPropertyOrder({"id","startDate","endDate","suspended"})
public class Store {

    @JsonProperty(value = "Id")
    private String Id = "1";
    @JsonProperty(value = "StartDate")
    private String StartDate = GenericMethods.currentDate()+"T00:00:00";
    @JsonProperty(value = "EndDate")
    private String EndDate = "2056-12-30T23:59:00";
    @JsonProperty(value = "Suspended")
    private String Suspended = "false";
}
