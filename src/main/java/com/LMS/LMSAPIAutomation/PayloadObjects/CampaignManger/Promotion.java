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
@JsonPropertyOrder({"promotionHeaderId","promotionActiveDays","promotionDetails","distribution","promotionsLevels","promotionsBuckets"})
public class Promotion {
    @JsonProperty(value = "PromotionHeaderId")
    private String PromotionHeaderId;
    @JsonProperty(value = "PromotionActiveDays")
    private PromotionActiveDays PromotionActiveDays;
    @JsonProperty(value = "PromotionDetails")
    private PromotionDetails PromotionDetails;
    @JsonProperty(value = "Distribution")
    private Distribution Distribution;
    @JsonProperty(value = "PromotionsLevels")
    private PromotionsLevels PromotionsLevels;
    @JsonProperty(value = "PromotionsBuckets")
    private PromotionsBuckets PromotionsBuckets;

}
