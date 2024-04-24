package com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"headerDescription","status","typeId","startDate","endDate","targetPopulationType"
        ,"requiredCoupon","redemptionLimitMaximum","redemptionLimitPerDay","redemptionLimitPerTransaction"
        ,"remarks","promotionReceiptDescription","additionalTypeId","continuityScope","triggerTiming","discountAllocationScope"
        ,"promotionFundingScope","ticketPrintingScope","meanOfPaymentScope","conflictScope","thresholdStatus","discountAllocationBit"
        ,"promotionFundingBit","rewardPerBucket","couponRewardsScope","promotionPriority","populationSegmentsOperator"
        ,"populationLocalSegmentsOperator","populationOfflineMode","segmentationMode","promotionGroupId","accountingCode"
        ,"accountingSubCode","returnItemEligibility","homeStoreScope","redemptionLimitScope","promotionFlowStatus","excludeItemsWithProhibitDiscount"
        ,"triggerItemsExcludedFromOtherPromotions","excludeTriggerItemsOfMarkedPromotions","memberRegistrationRequired","deductRegistrationCost","applyPartialRewardWithOptimization"
        ,"promotionRewardWillBeDisregardedByOtherPromotions","disregardRewardOfMarkedPromotions","missedOffer","digitalCouponRequired",
        "manualPriorityValue","excludeFromPriceCompare","isBarcodeOmniChannel","businessDescription"
        ,"allowOmniChannelCoupon","multiRedemptionCoupon","issuingSource","documentId","calculatePromotionThresholdExcludeTax"
        ,"itemRedemptionConfirmationRequired","calculatePromotionPostTax","discountAllocationFlag","registeredPromotionRedemption"})

public class PromotionDetails {
    @JsonProperty(value = "HeaderDescription")
    private String HeaderDescription;
    @JsonProperty(value = "Status")
    private String Status = "true";
    @JsonProperty(value = "TypeId")
    private String TypeId;
    @JsonProperty(value = "StartDate")
    private String StartDate = GenericMethods.currentDate() +"T00:00:00";
    @JsonProperty(value = "EndDate")
    private String EndDate = "2056-12-30T23:59:00";
    @JsonProperty(value = "TargetPopulationType")
    private String TargetPopulationType = "2";
    @JsonProperty(value = "RequiredCoupon")
    private String RequiredCoupon = "false";
    @JsonProperty(value = "RedemptionLimitMaximum")
    private String RedemptionLimitMaximum = "1";
    @JsonProperty(value = "RedemptionLimitPerDay")
    private String RedemptionLimitPerDay = "0";
    @JsonProperty(value = "RedemptionLimitPerTransaction")
    private String RedemptionLimitPerTransaction = "0";
    @JsonProperty(value = "Remarks")
    private String Remarks = "Test Remarks";
    @JsonProperty(value = "PromotionReceiptDescription")
    private String PromotionReceiptDescription ="Automation";
    @JsonProperty(value = "AdditionalTypeId")
    private String AdditionalTypeId = "0";
    @JsonProperty(value = "ContinuityScope")
    private String ContinuityScope = "1";
    @JsonProperty(value = "TriggerTiming")
    private String TriggerTiming = "true";
    @JsonProperty(value = "DiscountAllocationScope")
    private String DiscountAllocationScope = "5";
    @JsonProperty(value = "PromotionFundingScope")
    private String PromotionFundingScope = "1";
    @JsonProperty(value = "TicketPrintingScope")
    private String TicketPrintingScope = "0";
    @JsonProperty(value = "MeanOfPaymentScope")
    private String MeanOfPaymentScope = "2";
    @JsonProperty(value = "ConflictScope")
    private String ConflictScope = "1";
    @JsonProperty(value = "ThresholdStatus")
    private String ThresholdStatus = "1";
    @JsonProperty(value = "DiscountAllocationBit")
    private String DiscountAllocationBit = "1";
    @JsonProperty(value = "PromotionFundingBit")
    private String PromotionFundingBit = "1";
    @JsonProperty(value = "RewardPerBucket")
    private String RewardPerBucket = "0";
    @JsonProperty(value = "CouponRewardsScope")
    private String CouponRewardsScope = "2";
    @JsonProperty(value = "PromotionPriority")
    private String PromotionPriority = "1";
    @JsonProperty(value = "PopulationSegmentsOperator")
    private String PopulationSegmentsOperator = "1";
    @JsonProperty(value = "PopulationLocalSegmentsOperator")
    private String PopulationLocalSegmentsOperator = "1";
    @JsonProperty(value = "PopulationOfflineMode")
    private String PopulationOfflineMode = "1";
    @JsonProperty(value = "SegmentationMode")
    private String SegmentationMode = "0";
    @JsonProperty(value = "PromotionGroupId")
    private String PromotionGroupId = "0";
    @JsonProperty(value = "AccountingCode")
    private String AccountingCode;
    @JsonProperty(value = "AccountingSubCode")
    private String AccountingSubCode;
    @JsonProperty(value = "ReturnItemEligibility")
    private String ReturnItemEligibility = "0";
    @JsonProperty(value = "HomeStoreScope")
    private String HomeStoreScope = "1";
    @JsonProperty(value = "RedemptionLimitScope")
    private String RedemptionLimitScope = "1";
    @JsonProperty(value = "PromotionFlowStatus")
    private String PromotionFlowStatus = "1";
    @JsonProperty(value = "ExcludeItemsWithProhibitDiscount")
    private String ExcludeItemsWithProhibitDiscount = "0";
    @JsonProperty(value = "TriggerItemsExcludedFromOtherPromotions")
    private String TriggerItemsExcludedFromOtherPromotions = "0";
    @JsonProperty(value = "ExcludeTriggerItemsOfMarkedPromotions")
    private String ExcludeTriggerItemsOfMarkedPromotions = "0";
    @JsonProperty(value = "ExternalValidationRequired")
    private String ExternalValidationRequired = "0";
    @JsonProperty(value = "ExcludeRewardedItemsFromSpendConditionThreshold")
    private String ExcludeRewardedItemsFromSpendConditionThreshold = "0";
    @JsonProperty(value = "MemberRegistrationRequired")
    private String MemberRegistrationRequired = "0";
    @JsonProperty(value = "DeductRegistrationCost")
    private String DeductRegistrationCost = "0";
    @JsonProperty(value = "ApplyPartialRewardWithOptimization")
    private String ApplyPartialRewardWithOptimization = "false";
    @JsonProperty(value = "PromotionRewardWillBeDisregardedByOtherPromotions")
    private String PromotionRewardWillBeDisregardedByOtherPromotions = "false";
    @JsonProperty(value = "DisregardRewardOfMarkedPromotions")
    private String DisregardRewardOfMarkedPromotions = "false";
    @JsonProperty(value = "MissedOffer")
    private String MissedOffer = "false";
    @JsonProperty(value = "DigitalCouponRequired")
    private String DigitalCouponRequired = "false";
    @JsonProperty(value = "ManualPriorityValue")
    private String ManualPriorityValue = "0";
    @JsonProperty(value = "ExcludeFromPriceCompare")
    private String ExcludeFromPriceCompare = "false";
    @JsonProperty(value = "IsBarcodeOmniChannel")
    private String IsBarcodeOmniChannel = "0";
    @JsonProperty(value = "BusinessDescription")
    private String BusinessDescription;
    @JsonProperty(value = "AllowOmniChannelCoupon")
    private String AllowOmniChannelCoupon;
    @JsonProperty(value = "MultiRedemptionCoupon")
    private String MultiRedemptionCoupon;
    @JsonProperty(value = "IssuingSource")
    private String IssuingSource;
    @JsonProperty(value = "DocumentId")
    private String DocumentId;
    @JsonProperty(value = "CalculatePromotionThresholdExcludeTax")
    private String CalculatePromotionThresholdExcludeTax = "0";
    @JsonProperty(value = "ItemRedemptionConfirmationRequired")
    private String ItemRedemptionConfirmationRequired = "false";
    @JsonProperty(value = "CalculatePromotionPostTax")
    private String CalculatePromotionPostTax = "false";
    @JsonProperty(value = "DiscountAllocationFlag")
    private String DiscountAllocationFlag = "true";
    @JsonProperty(value = "RegisteredPromotionRedemption")
    private String RegisteredPromotionRedemption = "0";
}
