/**
 * 
 */
package com.LMS.LMSAPIAutomation.Payload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger.*;
import com.LMS.LMSAPIAutomation.RequestTemplates.PromotionTemplate;

/**
 * @author sa185402
 *
 */
public class CampaignManagerPayload {

//	static Random rnd = new Random();
//	public static int singleConditionPromotionHeaderId = rnd.nextInt(99999);
//	public static int multipleConditionPromotionHeaderId = rnd.nextInt(99999);
	
	
//	public static String payload(String Payload,HashMap<String, String> parameters) {
//
//		HashMap<String, String> hs = new HashMap<String,String>();
//		hs.put("singleCondition_Promotion", singleCondition_Promotion(parameters.get("promotionExternalId"), parameters.get("retailerId")));
////		hs.put("Multicondition_Promotion", Multicondition_Promotion(parameters.get("promotionExternalId"), parameters.get("retailerId")));
//		hs.put("MultiCondition_Coupon", multiCondition_Coupon(parameters.get("promotionExternalId"), parameters.get("retailerId"), parameters.get("documentID")));
//		hs.put("GetPromotionDetails", getPromotionDetails(parameters.get("retailerId"),parameters.get("promotionHeaderId")));
//
//		return hs.get(Payload);
//	}

	public Object payload(String Payload,HashMap<String, String> parameters,io.cucumber.datatable.DataTable userTable){
		List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

		switch (Payload.toUpperCase()){
			case "MULTICONDITION_PROMOTION":
				return Multicondition_Promotion(parameters,att);
			case "SINGLECONDITION":
				return singleCondition_Promotion(parameters,att);
		}
		return "";
	}
	
	public static Object Multicondition_Promotion(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String multipleConditionPromotionHeaderId = parameters.get("promotionExternalId");
		String memberRegistrationRequired = att.get(0).get("memberRegistrationRequired")+"";
		String retailerId = parameters.get("retailerId");
//		String retailerId = "1";


		String HeaderDescription = "Automation "+multipleConditionPromotionHeaderId;
		PromotionActiveDays promotionActiveDays = new PromotionActiveDays();

		PromotionDetails promotionDetails = new PromotionDetails();
		promotionDetails.setHeaderDescription(HeaderDescription);
		promotionDetails.setTypeId("3");

		ArrayList<Store> storeArrayList = new ArrayList<Store>();
		storeArrayList.add(new Store());
		Distribution distribution = new Distribution(storeArrayList);

		ArrayList<Threshold> thresholdArrayList = new ArrayList<Threshold>();
		thresholdArrayList.add(new Threshold("1","2","1",null));
//		thresholdArrayList.add(new Threshold("2","2","2",null));

		ArrayList<PromotionsLevel> promotionsLevelArrayList = new ArrayList<PromotionsLevel>();
		promotionsLevelArrayList.add(new PromotionsLevel("1",new Reward(),new Thresholds(thresholdArrayList)));
		PromotionsLevels promotionsLevels = new PromotionsLevels(promotionsLevelArrayList);

		ArrayList<BucketEntity> bucketEntityArrayList = new ArrayList<BucketEntity>();
		bucketEntityArrayList.add(new BucketEntity("1","0","true","false"));

		PromotionsBucket promotionsBucket = new PromotionsBucket();
		promotionsBucket.setBucketEntity(bucketEntityArrayList);

		ArrayList<PromotionsBucket> promotionsBucketArrayList = new ArrayList<PromotionsBucket>();
		promotionsBucketArrayList.add(promotionsBucket);

		PromotionsBuckets promotionsBuckets = new PromotionsBuckets(promotionsBucketArrayList);


		ArrayList<Retailer> retailers = new ArrayList<Retailer>();
		ArrayList<Promotion> promotion = new ArrayList<Promotion>();
		promotion.add(new Promotion(multipleConditionPromotionHeaderId,promotionActiveDays,promotionDetails,distribution,promotionsLevels,promotionsBuckets));
		retailers.add(new Retailer(retailerId,new Promotions(promotion)));


		com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger.HQL_Promotion_Interface hql_promotion_interface = new com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger.HQL_Promotion_Interface(retailers);


		return new PromotionTemplate(hql_promotion_interface);
	}

	public static String multiCondition_Coupon(String multipleConditionPromotionHeaderId, String retailerId, String DocumentId){

		String HeaderDescription = "Automation "+multipleConditionPromotionHeaderId;

		return "{\n" +
				"  \"HQL_Promotion_Interface\": {\n" +
				"    \"Retailer\": [\n" +
				"      {\n" +
				"        \"Id\": \""+retailerId+"\",\n" +
				"        \"Promotions\": {\n" +
				"          \"Promotion\": [\n" +
				"            {\n" +
				"              \"PromotionHeaderId\": \""+multipleConditionPromotionHeaderId+"\",\n" +
				"              \"PromotionActiveDays\": {\n" +
				"                \"ActiveOnSunday\": \"true\",\n" +
				"                \"ActiveOnMonday\": \"true\",\n" +
				"                \"ActiveOnTuesday\": \"true\",\n" +
				"                \"ActiveOnWednesday\": \"true\",\n" +
				"                \"ActiveOnThursday\": \"true\",\n" +
				"                \"ActiveOnFriday\": \"true\",\n" +
				"                \"ActiveOnSaturday\": \"true\",\n" +
				"                \"StartTime\": \"00:00:00\",\n" +
				"                \"EndTime\": \"23:59:00\"\n" +
				"              },\n" +
				"              \"PromotionDetails\": {\n" +
				"                \"HeaderDescription\": \""+HeaderDescription+"\",\n" +
				"                \"Status\": \"true\",\n" +
				"                \"TypeId\": \"3\",\n" +
				"                \"StartDate\": \""+GenericMethods.currentDate()+"T00:00:00\",\n" +
				"                \"EndDate\": \"2056-12-30T23:59:00\",\n" +
				"                \"TargetPopulationType\": \"2\",\n" +
				"                \"RequiredCoupon\": \"true\",\n" +
				"                \"RedemptionLimitMaximum\": \"1\",\n" +
				"                \"RedemptionLimitPerDay\": \"0\",\n" +
				"                \"RedemptionLimitPerTransaction\": \"0\",\n" +
				"                \"Remarks\": \"Test Remarks\",\n" +
				"                \"PromotionReceiptDescription\": \""+HeaderDescription+"\",\n" +
				"                \"AdditionalTypeId\": \"0\",\n" +
				"                \"ContinuityScope\": \"1\",\n" +
				"                \"TriggerTiming\": \"true\",\n" +
				"                \"DiscountAllocationScope\": \"5\",\n" +
				"                \"PromotionFundingScope\": \"1\",\n" +
				"                \"TicketPrintingScope\": \"0\",\n" +
				"                \"MeanOfPaymentScope\": \"2\",\n" +
				"                \"ConflictScope\": \"1\",\n" +
				"                \"ThresholdStatus\": \"1\",\n" +
				"                \"DiscountAllocationBit\": \"1\",\n" +
				"                \"PromotionFundingBit\": \"1\",\n" +
				"                \"RewardPerBucket\": \"0\",\n" +
				"                \"CouponRewardsScope\": \"2\",\n" +
				"                \"PromotionPriority\": \"1\",\n" +
				"                \"PopulationSegmentsOperator\": \"1\",\n" +
				"                \"PopulationLocalSegmentsOperator\": \"1\",\n" +
				"                \"PopulationOfflineMode\": \"1\",\n" +
				"                \"SegmentationMode\": \"0\",\n" +
				"                \"PromotionGroupId\": \"0\",\n" +
				"                \"AccountingCode\": \"\",\n" +
				"                \"AccountingSubCode\": \"\",\n" +
				"                \"ReturnItemEligibility\": \"0\",\n" +
				"                \"HomeStoreScope\": \"1\",\n" +
				"                \"RedemptionLimitScope\": \"1\",\n" +
				"                \"PromotionFlowStatus\": \"1\",\n" +
				"                \"ExcludeItemsWithProhibitDiscount\": \"0\",\n" +
				"                \"TriggerItemsExcludedFromOtherPromotions\": \"0\",\n" +
				"                \"ExcludeTriggerItemsOfMarkedPromotions\": \"0\",\n" +
				"                \"ExternalValidationRequired\": \"0\",\n" +
				"                \"ExcludeRewardedItemsFromSpendConditionThreshold\": \"0\",\n" +
				"                \"MemberRegistrationRequired\": \"1\",\n" +
				"                \"DeductRegistrationCost\": \"0\",\n" +
				"                \"ApplyPartialRewardWithOptimization\": \"false\",\n" +
				"                \"PromotionRewardWillBeDisregardedByOtherPromotions\": \"false\",\n" +
				"                \"DisregardRewardOfMarkedPromotions\": \"false\",\n" +
				"                \"MissedOffer\": \"false\",\n" +
				"                \"DigitalCouponRequired\": \"false\",\n" +
				"                \"ManualPriorityValue\": \"0\",\n" +
				"                \"ExcludeFromPriceCompare\": \"false\",\n" +
				"                \"IsBarcodeOmniChannel\": \"0\",\n" +
				"                \"BusinessDescription\": \"Coupon\",\n" +
				"                \"AllowOmniChannelCoupon\": \"0\",\n" +
				"                \"MultiRedemptionCoupon\": \"1\",\n" +
				"                \"IssuingSource\": \"0\",\n" +
				"                \"DocumentId\": \""+DocumentId+"\",\n" +
				"                \"CalculatePromotionThresholdExcludeTax\": \"0\",\n" +
				"                \"ItemRedemptionConfirmationRequired\": \"false\",\n" +
				"                \"CalculatePromotionPostTax\": \"false\",\n" +
				"                \"DiscountAllocationFlag\": \"true\",\n" +
				"                \"RegisteredPromotionRedemption\": \"0\"\n" +
				"              },\n" +
				"              \"Distribution\": {\n" +
				"                \"Store\": [\n" +
				"                  {\n" +
				"                    \"Id\": \"0\",\n" +
				"                    \"StartDate\": \""+GenericMethods.currentDate()+"T00:00:00\",\n" +
				"                    \"EndDate\": \"2056-12-30T23:59:00\",\n" +
				"                    \"Suspended\": \"false\"\n" +
				"                  }\n" +
				"                ]\n" +
				"              },\n" +
				"              \"PromotionsLevels\": {\n" +
				"                \"PromotionsLevel\": [\n" +
				"                  {\n" +
				"                    \"Id\": \"1\",\n" +
				"                    \"Reward\": {\n" +
				"                      \"RewardTemplateId\": \"60\",\n" +
				"                      \"RewardValue\": \"2.98\",\n" +
				"                      \"RewardValueType\": \"2\",\n" +
				"                      \"LimitDiscountScope\": \"0\",\n" +
				"                      \"LimitItemScope\": \"1\",\n" +
				"                      \"LimitItemCount\": \"1\",\n" +
				"                      \"TimingScope\": \"0\",\n" +
				"                      \"ReducePriceScope\": \"0\",\n" +
				"                      \"MABalanceType\": \"101\",\n" +
				"                      \"ReduceMAByRewardValue\": \"1\",\n" +
				"                      \"LimitRewardMAAmount\": \"1\",\n" +
				"                      \"RewardEnteredByCashier\": \"0\",\n" +
				"                      \"AccumulateDiscountIntoMemberAccount\": \"0\",\n" +
				"                      \"ReduceMemberAccountValueScope\": \"1\"\n" +
				"                    },\n" +
				"                    \"Thresholds\": {\n" +
				"                      \"Threshold\": [\n" +
				"                        {\n" +
				"                          \"BucketId\": \"1\",\n" +
				"                          \"ThresholdTypeId\": \"4\",\n" +
				"                          \"ThresholdValue\": \"1\"\n" +
				"                        }\n" +
				"                      ]\n" +
				"                    }\n" +
				"                  }\n" +
				"                ]\n" +
				"              },\n" +
				"              \"PromotionsBuckets\": {\n" +
				"                \"PromotionsBucket\": [\n" +
				"                  {\n" +
				"                    \"Id\": \"1\",\n" +
				"                    \"QtySize\": \"2\",\n" +
				"                    \"QtySizeMinValue\": \"0.0000\",\n" +
				"                    \"QtySizeMaxValue\": \"0.0000\",\n" +
				"                    \"ThresholdAppliesTo\": \"0\",\n" +
				"                    \"ParticipateInReward\": \"true\",\n" +
				"                    \"RewardCalculationStartsFromMinValue\": \"0\",\n" +
				"                    \"AndOrOperator\": \"true\",\n" +
				"                    \"AllocateDiscountBreakdown\": \"true\",\n" +
				"                    \"LimitRewardedByConditionSpend\": \"false\",\n" +
				"                    \"BucketTriggerItemsWillBeExcludedFromOtherPromotions\": \"false\",\n" +
				"                    \"BucketEntity\": [\n" +
				"                      {\n" +
				"                        \"EntityId\": \"1\",\n" +
				"                        \"EntityType\": \"0\",\n" +
				"                        \"AndOrOperator\": \"true\",\n" +
				"                        \"Exclude\": \"true\"\n" +
				"                      }\n" +
				"                    ]\n" +
				"                  }\n" +
				"                ]\n" +
				"              }\n" +
				"            }\n" +
				"          ]\n" +
				"        }\n" +
				"      }\n" +
				"    ]\n" +
				"  }\n" +
				"}";
	}
	public static Object singleCondition_Promotion(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String singleConditionPromotionHeaderId = parameters.get("promotionExternalId");
		String memberRegistrationRequired = att.get(0).get("memberRegistrationRequired")+"";
		String retailerId = parameters.get("retailerId");
//		String retailerId = "1";
		String HeaderDescription = "Automation_"+singleConditionPromotionHeaderId;

//		PromotionActiveDays
		PromotionActiveDays promotionActiveDays = new PromotionActiveDays();

//		Promotion Details
		PromotionDetails promotionDetails = new PromotionDetails();
		promotionDetails.setHeaderDescription(HeaderDescription);
		promotionDetails.setTypeId("2");
		promotionDetails.setTargetPopulationType("1");
		promotionDetails.setRedemptionLimitScope("0");
		promotionDetails.setMemberRegistrationRequired(memberRegistrationRequired);
		promotionDetails.setAllowOmniChannelCoupon("0");
		promotionDetails.setRegisteredPromotionRedemption(null);

//		Distribution
		ArrayList<Store> storeArrayList = new ArrayList<Store>();
		storeArrayList.add(new Store());
		Distribution distribution = new Distribution(storeArrayList);

//		Promotion Levels
		ArrayList<Threshold> thresholdArrayList = new ArrayList<Threshold>();
		thresholdArrayList.add(new Threshold("1","2","1",null));

		ArrayList<PromotionsLevel> promotionsLevelArrayList = new ArrayList<PromotionsLevel>();
		promotionsLevelArrayList.add(new PromotionsLevel("1",new Reward(),new Thresholds(thresholdArrayList)));
		PromotionsLevels promotionsLevels = new PromotionsLevels(promotionsLevelArrayList);

//		Promotion Bucket
		ArrayList<BucketEntity> bucketEntityArrayList = new ArrayList<BucketEntity>();
		bucketEntityArrayList.add(new BucketEntity("1","0","true","false"));

		PromotionsBucket promotionsBucket = new PromotionsBucket();
		promotionsBucket.setBucketEntity(bucketEntityArrayList);

		ArrayList<PromotionsBucket> promotionsBucketArrayList = new ArrayList<PromotionsBucket>();
		promotionsBucketArrayList.add(promotionsBucket);

		PromotionsBuckets promotionsBuckets = new PromotionsBuckets(promotionsBucketArrayList);

		ArrayList<Retailer> retailers = new ArrayList<Retailer>();
		ArrayList<Promotion> promotion = new ArrayList<Promotion>();
		promotion.add(new Promotion(singleConditionPromotionHeaderId,promotionActiveDays,promotionDetails,distribution,promotionsLevels,promotionsBuckets));
		retailers.add(new Retailer(retailerId,new Promotions(promotion)));


		com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger.HQL_Promotion_Interface hql_promotion_interface = new com.LMS.LMSAPIAutomation.PayloadObjects.CampaignManger.HQL_Promotion_Interface(retailers);


		return new PromotionTemplate(hql_promotion_interface);
		
	}

	public static String getPromotionDetails(String retailerId, String promotionID) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:GetPromotionByInternalId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:retailerId>"+retailerId+"</hql:retailerId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:promotions>\r\n"
				+ "            <!--Zero or more repetitions:-->\r\n"
				+ "            <hql:int>"+promotionID+"</hql:int>\r\n"
				+ "         </hql:promotions>\r\n"
				+ "      </hql:GetPromotionByInternalId>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
	}
}
