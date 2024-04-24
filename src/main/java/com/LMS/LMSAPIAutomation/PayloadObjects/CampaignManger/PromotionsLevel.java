package com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger;

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
@JsonPropertyOrder({"Id","Reward","Thresholds"})
public class PromotionsLevel {
    @JsonProperty(value = "Id")
    private String Id;
    @JsonProperty(value = "Reward")
    private Reward Reward;
    @JsonProperty(value = "Thresholds")
    private Thresholds Thresholds;

}
