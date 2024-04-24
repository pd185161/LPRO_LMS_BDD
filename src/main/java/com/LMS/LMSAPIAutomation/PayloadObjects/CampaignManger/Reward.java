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
@JsonPropertyOrder({"rewardTemplateId","rewardValue","rewardValueType","limitDiscountScope"
        ,"limitItemScope","limitItemCount","timingScope","reducePriceScope"
        ,"mABalanceType","reduceMAByRewardValue","limitRewardMAAmount"
        ,"rewardEnteredByCashier","accumulateDiscountIntoMemberAccount","reduceMemberAccountValueScope"})
public class Reward {
    @JsonProperty(value = "RewardTemplateId")
    private String RewardTemplateId = "2";
    @JsonProperty(value = "RewardValue")
    private String RewardValue = "1.00";
    @JsonProperty(value = "RewardValueType")
    private String RewardValueType = "2";
    @JsonProperty(value = "LimitDiscountScope")
    private String LimitDiscountScope = "0";
    @JsonProperty(value = "LimitItemScope")
    private String LimitItemScope = "1";
    @JsonProperty(value = "LimitItemCount")
    private String LimitItemCount = "1";
    @JsonProperty(value = "TimingScope")
    private String TimingScope = "0";
    @JsonProperty(value = "ReducePriceScope")
    private String ReducePriceScope = "0";
    @JsonProperty(value = "MABalanceType")
    private String MABalanceType = "101";
    @JsonProperty(value = "ReduceMAByRewardValue")
    private String ReduceMAByRewardValue = "1";
    @JsonProperty(value = "LimitRewardMAAmount")
    private String LimitRewardMAAmount = "1";
    @JsonProperty(value = "RewardEnteredByCashier")
    private String RewardEnteredByCashier = "0";
    @JsonProperty(value = "AccumulateDiscountIntoMemberAccount")
    private String AccumulateDiscountIntoMemberAccount = "0";
    @JsonProperty(value = "ReduceMemberAccountValueScope")
    private String ReduceMemberAccountValueScope = "1";
}
