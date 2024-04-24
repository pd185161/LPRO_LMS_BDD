Feature: Promotion Import General Validations

  Scenario: Member Service Extract values from DB
    Given Retrieve all the Required parameter values from DataBase

  @PromotionImport
  Scenario Outline: Promotion Import- Single Item(Type=1)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | Name                                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 1      | SIPromo_1                   | SIPromo_1         | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 6             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 2               | 1              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 1                | 1           | 0               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | false   |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-01.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |

  @PromotionImport
  Scenario Outline: Promotion Import- Single Condition(Type=2)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | Name                                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 2      | SCPromo_1                   | SCPromo_1         | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 6             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 2               | 1              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 1                | 1           | 0               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | false   |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-02.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |

  @PromotionImport
  Scenario Outline: Promotion Import- Multi Condition(Type=3)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 3      | MCPromo_1                   | MCPromo_1         | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 6             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 2               | 1              |
      | Threshold | 1     | 2        | 2               | 2              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 1                | 1           | 0               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward | Name    |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                | Bucket1 |
      | PromotionsBucket | 1     | 2  | 2       | true                      | true          | true                | Bucket2 |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | false   |
      | BucketEntity | 1     | 2        | 0          | true          | false   |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-03.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |

  @PromotionImport
  Scenario Outline: Promotion Import- Total Ticket(Type=4)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | Name                                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 4      | TTPromo_1                   | TTPromo_1         | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 6             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 4               | 1              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 60               | 1           | 2               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | true    |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-04.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |

  @PromotionImport
  Scenario Outline: Promotion Import- Manual Item Discount(Type=11)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | Name                                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 11     | MIDPromo_1                  | MIDPromo_1        | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 1             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 2               | 1              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 1                | 1           | 2               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | false   |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-11.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |

  @PromotionImport
  Scenario Outline: Promotion Import- Manual Ticket Discount(Type=12)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | Name                                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 12     | MTDPromo_1                  | MTDPromo_1        | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 1             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 2               | 1              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 60               | 1           | 2               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | true    |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-12.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |

  @PromotionImport
  Scenario Outline: Promotion Import- Targeted Manual Item Discount(Type=14)
    Given Validate the Promotion Exist
    Given Configure Import XML file with following all nodes
      | NodesNotRequired            | SampleXMLFle     |
      | PromotionGroups             | <sampleFilePath> |
      | PromotionActiveDays         | <sampleFilePath> |
      | EnhancedPromotionActiveDays | <sampleFilePath> |
      | Messages                    | <sampleFilePath> |
      | PromotionLoyaltySegments    | <sampleFilePath> |
      | LinkedPromotions            | <sampleFilePath> |
      | PromotionAttributes         | <sampleFilePath> |
      | Translations                | <sampleFilePath> |
      | CheckoutGroups              | <sampleFilePath> |
      | MarketingAttributes         | <sampleFilePath> |
      | PromotionSchemes            | <sampleFilePath> |
      | Legend                      | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element          | attributeName                                       | index |
      | Promotions       | OfferProviderName                                   | 0     |
      | Promotion        | Action                                              | 0     |
      | PromotionDetails | AccountingCode                                      | 0     |
      | PromotionDetails | AccountingSubCode                                   | 0     |
      | PromotionDetails | AdditionalTypeId                                    | 0     |
      | PromotionDetails | AllowOmniChannelCoupon                              | 0     |
      | PromotionDetails | ApplyPartialRewardWithOptimization                  | 0     |
      | PromotionDetails | CalculatePromotionOnGrossPrice                      | 0     |
      | PromotionDetails | CalculatePromotionPostTax                           | 0     |
      | PromotionDetails | CalculatePromotionThresholdExcludeTax               | 0     |
      | PromotionDetails | ConflictGroupId                                     | 0     |
      | PromotionDetails | CouponBusinessId                                    | 0     |
      | PromotionDetails | CouponInstanceDescription                           | 0     |
      | PromotionDetails | CouponPrefix                                        | 0     |
      | PromotionDetails | CouponRewardsScope                                  | 0     |
      | PromotionDetails | DeductRegistrationCost                              | 0     |
      | PromotionDetails | DefaultRedemptionLimit                              | 0     |
      | PromotionDetails | DigitalCouponRequired                               | 0     |
      | PromotionDetails | DisregardItemMinimumPrice                           | 0     |
      | PromotionDetails | DisregardRewardOfMarkedPromotions                   | 0     |
      | PromotionDetails | DocumentId                                          | 0     |
      | PromotionDetails | ExcludeFromPriceCompare                             | 0     |
      | PromotionDetails | ExcludeItemsWithProhibitDiscount                    | 0     |
      | PromotionDetails | ExcludeRewardedItemsFromSpendConditionThreshold     | 0     |
      | PromotionDetails | ExcludeTriggerItemsOfMarkedPromotions               | 0     |
      | PromotionDetails | ExternalGroupId                                     | 0     |
      | PromotionDetails | ExternalValidationRequired                          | 0     |
      | PromotionDetails | ItemRedemptionConfirmationRequired                  | 0     |
      | PromotionDetails | LabelDescription                                    | 0     |
      | PromotionDetails | LabelFormat1                                        | 0     |
      | PromotionDetails | LabelFormat2                                        | 0     |
      | PromotionDetails | LevelRepetition                                     | 0     |
      | PromotionDetails | LimitedToHomeStoreOnly                              | 0     |
      | PromotionDetails | MarkdownCategoryId                                  | 0     |
      | PromotionDetails | MarkdownCategoryLevelId                             | 0     |
      | PromotionDetails | MaximumItemPrice                                    | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderScope                      | 0     |
      | PromotionDetails | MeanOfPaymentSharedTenderValue                      | 0     |
      | PromotionDetails | MeanOfPaymentTenderId                               | 0     |
      | PromotionDetails | MemberAccountId                                     | 0     |
      | PromotionDetails | MemberAccountValue                                  | 0     |
      | PromotionDetails | MinimumItemPrice                                    | 0     |
      | PromotionDetails | MinimumNumberApplyTo                                | 0     |
      | PromotionDetails | MinimumNumberOfItems                                | 0     |
      | PromotionDetails | MinimumPurchaseAmount                               | 0     |
      | PromotionDetails | MissedOffer                                         | 0     |
      | PromotionDetails | PauseFromExecutionOnStores                          | 0     |
      | PromotionDetails | PromotionDraftStatus                                | 0     |
      | PromotionDetails | PromotionFlowStatus                                 | 0     |
      | PromotionDetails | RdmConfirmationMsg                                  | 0     |
      | PromotionDetails | RedeemDigitalCoupon                                 | 0     |
      | PromotionDetails | RedemptionLimitMaximum                              | 0     |
      | PromotionDetails | RedemptionLimitPerDay                               | 0     |
      | PromotionDetails | RedemptionLimitPerTransaction                       | 0     |
      | PromotionDetails | RegisteredPromotionRedemption                       | 0     |
      | PromotionDetails | RequiredCoupon                                      | 0     |
      | PromotionDetails | ReturnItemEligibility                               | 0     |
      | PromotionDetails | SharedFundingScope                                  | 0     |
      | PromotionDetails | SharedFundingValue                                  | 0     |
      | PromotionDetails | SponsorSupplierId                                   | 0     |
      | PromotionDetails | TicketPrintingScope                                 | 0     |
      | PromotionDetails | TicketPrintingText                                  | 0     |
      | PromotionDetails | TotalTriggerLimit                                   | 0     |
      | PromotionDetails | TriggerItemsExcludedFromOtherPromotions             | 0     |
      | PromotionDetails | UOMLimit                                            | 0     |
      | PromotionDetails | UOMLimitType                                        | 0     |
      | Store            | Action                                              | 0     |
      | PromotionsLevel  | ExternalLevelRef                                    | 0     |
      | Reward           | AddTaxAmountToRewardAmount                          | 0     |
      | Reward           | AdditionalPointsExpirationValue                     | 0     |
      | Reward           | CalculatePromotionRewardExcludeTax                  | 0     |
      | Reward           | CalculateRewardOnPurchasedAmount                    | 0     |
      | Reward           | CampaignLimitsScope                                 | 0     |
      | Reward           | CashierMaximumManualMABalanceType                   | 0     |
      | Reward           | CashierMaximumManualMAId                            | 0     |
      | Reward           | CashierMaximumManualRewardSource                    | 0     |
      | Reward           | CompensationDocumentId                              | 0     |
      | Reward           | CompensationStartingFrom                            | 0     |
      | Reward           | CompensationValidForXDays                           | 0     |
      | Reward           | CompensationValidFrom                               | 0     |
      | Reward           | CompensationValidScope                              | 0     |
      | Reward           | CompensationValidTo                                 | 0     |
      | Reward           | ConnectedSegmentId                                  | 0     |
      | Reward           | DigitalCouponRequired                               | 0     |
      | Reward           | DisconnectedSegmentId                               | 0     |
      | Reward           | DiscountsAccumulatingMemberAccountId                | 0     |
      | Reward           | DocumentId                                          | 0     |
      | Reward           | DocumentStartingFrom                                | 0     |
      | Reward           | DocumentType                                        | 0     |
      | Reward           | DocumentValidForXDays                               | 0     |
      | Reward           | DocumentValidFrom                                   | 0     |
      | Reward           | DocumentValidScope                                  | 0     |
      | Reward           | DocumentValidTo                                     | 0     |
      | Reward           | IncreaseMemberAccountId                             | 0     |
      | Reward           | IncreaseMemberAccountType                           | 0     |
      | Reward           | InjectRewardFromDAId                                | 0     |
      | Reward           | InjectRewardFromItemPrice                           | 0     |
      | Reward           | InjectRewardFromMAId                                | 0     |
      | Reward           | IssueCompensationDocumentScope                      | 0     |
      | Reward           | IssueDigitalCoupon                                  | 0     |
      | Reward           | IssueOmniChannelCoupon                              | 0     |
      | Reward           | LimitRewardAmount                                   | 0     |
      | Reward           | LimitRewardMAAmount                                 | 0     |
      | Reward           | LimitRewardMAScope                                  | 0     |
      | Reward           | LimitRewardValue                                    | 0     |
      | Reward           | LimitUOMScope                                       | 0     |
      | Reward           | MABalanceType                                       | 0     |
      | Reward           | ManualEntryAllowed                                  | 0     |
      | Reward           | ManualRewardValueScope                              | 0     |
      | Reward           | MaximumPromotionRedemptions                         | 0     |
      | Reward           | MaximumPromotionRewards                             | 0     |
      | Reward           | MaximumSpendForReward                               | 0     |
      | Reward           | MinimumManualMABalanceType                          | 0     |
      | Reward           | MinimumManualMAValue                                | 0     |
      | Reward           | PercentLimitationScope                              | 0     |
      | Reward           | RecalculateTaxAfterPromotionDiscount                | 0     |
      | Reward           | ReduceMAByRewardValue                               | 0     |
      | Reward           | ReduceMemberAccountId                               | 0     |
      | Reward           | ReduceMemberAccountScope                            | 0     |
      | Reward           | ReduceMemberAccountValidationAppliesTo              | 0     |
      | Reward           | ReduceMemberAccountValue                            | 0     |
      | Reward           | ReduceMemberAccountValueScope                       | 0     |
      | Reward           | RewardIntoMAExpirationValue                         | 0     |
      | Reward           | RewardLimitationAmount                              | 0     |
      | Reward           | RewardLimitationScope                               | 0     |
      | Reward           | RewardPointsExpirationValue                         | 0     |
      | Reward           | TaxExecutionMethod                                  | 0     |
      | Reward           | TenderIDForTax                                      | 0     |
      | Reward           | TimingMemberAccountScope                            | 0     |
      | Reward           | TriggerValueForIncentiveMsg                         | 0     |
      | Reward           | AccumulateDiscountIntoMemberAccount                 | 0     |
      | Threshold        | ThresholdSubTypeId                                  | 0     |
      | PromotionsBucket | ApplyOnGrossTenderAmount                            | 0     |
      | PromotionsBucket | BucketExcludeTriggerItemsOfMarkedPromotions         | 0     |
      | PromotionsBucket | BucketTriggerItemsWillBeExcludedFromOtherPromotions | 0     |
      | PromotionsBucket | DeductTenderAmountFromQualifyingTicketSpend         | 0     |
      | PromotionsBucket | IncentiveMsgAppliesTo                               | 0     |
      | PromotionsBucket | LimitRewardedByConditionSpend                       | 0     |
      | PromotionsBucket | MinimumRequiredValue                                | 0     |
      | PromotionsBucket | Name                                                | 0     |
      | PromotionsBucket | QtySizeMaxValue                                     | 0     |
      | PromotionsBucket | QtySizeMinValue                                     | 0     |
      | PromotionsBucket | RewardCalculationStartsFromMinValue                 | 0     |
      | PromotionsBucket | RoundingToMultipleOf                                | 0     |
      | PromotionsBucket | ThresholdAppliesTo                                  | 0     |
      | BucketEntity     | EntityAdditionalName                                | 0     |
      | BucketEntity     | EntityAdditionalValue                               | 0     |
      | BucketEntity     | EntityLevel                                         | 0     |
      | BucketEntity     | EntityOperator                                      | 0     |
      | BucketEntity     | EntityValue                                         | 0     |
      | BucketEntity     | EntityViewId                                        | 0     |
      | BucketEntity     | ItemRedemptionLimit                                 | 0     |
      | BucketEntity     | MultiRewardValue                                    | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | PromotionHeaderId |
      | Promotion | 0     | NA                |
    And Update Import Element with respective attribute values
      | Nodes            | Index | TypeId | PromotionReceiptDescription | HeaderDescription | StartDate           | EndDate             | Status | ConflictScope | ContinuityScope | DiscountAllocationFlag | DiscountAllocationScope | HomeStoreScope | ManualPriorityValue | MeanOfPaymentScope | MemberRegistrationRequired | PopulationLocalSegmentsOperator | PopulationOfflineMode | PopulationSegmentsOperator | PromotionFundingScope | PromotionRewardWillBeDisregardedByOtherPromotions | RedemptionLimitScope | Remarks | SegmentationMode | TargetPopulationType | TriggerTiming |
      | PromotionDetails | 0     | 14     | TMIDPromo_1                 | TMIDPromo_1       | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | true   | 1             | 1               | true                   | 5                       | 1              | 0                   | 2                  | 1                          | 1                               | 1                     | 1                          | 1                     | false                                             | 0                    | test    | 0                | 2                    | true          |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id | StartDate           | EndDate             | Suspended |
      | Store | 0     | 0  | 2023-08-27T00:00:00 | 2056-12-30T23:59:00 | false     |
    And Update Import Element with respective attribute values
      | Nodes           | Index | Id |
      | PromotionsLevel | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | BucketId | ThresholdTypeId | ThresholdValue |
      | Threshold | 0     | 1        | 2               | 1              |
    And Update Import Element with respective attribute values
      | Nodes  | Index | RewardTemplateId | RewardValue | RewardValueType | LimitDiscountScope | LimitItemCount | LimitItemScope | MSUQuantity | ReducePriceScope | RewardEnteredByCashier | TimingScope |
      | Reward | 0     | 2                | 1           | 2               | 0                  | 1              | 1              | 0           | 0                | 0                      | 0           |
    And Update Import Element with respective attribute values
      | Nodes            | Index | Id | QtySize | AllocateDiscountBreakdown | AndOrOperator | ParticipateInReward |
      | PromotionsBucket | 0     | 1  | 2       | true                      | true          | true                |
    And Update Import Element with respective attribute values
      | Nodes        | Index | EntityId | EntityType | AndOrOperator | Exclude |
      | BucketEntity | 0     | 1        | 0          | true          | false   |
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                         | importFilepath                                               | serverBatchFilepath                                     | batchFileName        | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                | errorFile                              | batProcess       |
      | ./resources/#SamplePromotionImport.xml | ./target/BatchFiles/PromotionImport-2023-08-27T035033-14.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_PromotionImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\hql\exe\BatchManager\\ | C:\hql\exe\BatchManager\promotions.txt | C:\hql\exe\BatchManager\promotions.err | IMPORTPROMOTIONS |