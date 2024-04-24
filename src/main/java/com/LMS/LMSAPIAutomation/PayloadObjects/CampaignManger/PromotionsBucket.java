package com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","qtySize","qtySizeMinValue","qtySizeMaxValue","thresholdAppliesTo","participateInReward","rewardCalculationStartsFromMinValue"
        ,"qndOrOperator","qllocateDiscountBreakdown","limitRewardedByConditionSpend","bucketTriggerItemsWillBeExcludedFromOtherPromotions"})
public class PromotionsBucket {
    @JsonProperty(value = "Id")
    private String Id = "1";
    @JsonProperty(value = "QtySize")
    private String QtySize = "2";
    @JsonProperty(value = "QtySizeMinValue")
    private String QtySizeMinValue = "0.0000";
    @JsonProperty(value = "QtySizeMaxValue")
    private String QtySizeMaxValue = "0.0000";
    @JsonProperty(value = "ThresholdAppliesTo")
    private String ThresholdAppliesTo = "0";
    @JsonProperty(value = "ParticipateInReward")
    private String ParticipateInReward = "true";
    @JsonProperty(value = "RewardCalculationStartsFromMinValue")
    private String RewardCalculationStartsFromMinValue = "0";
    @JsonProperty(value = "AndOrOperator")
    private String AndOrOperator = "true";
    @JsonProperty(value = "AllocateDiscountBreakdown")
    private String AllocateDiscountBreakdown = "true";
    @JsonProperty(value = "LimitRewardedByConditionSpend")
    private String LimitRewardedByConditionSpend = "false";
    @JsonProperty(value = "BucketTriggerItemsWillBeExcludedFromOtherPromotions")
    private String BucketTriggerItemsWillBeExcludedFromOtherPromotions = "false";
    @JsonProperty(value = "BucketEntity")
    private ArrayList<BucketEntity> BucketEntity;
}
