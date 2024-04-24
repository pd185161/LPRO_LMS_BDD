package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"memberInternalKey","memberExternalId","isMainMember","lastName","firstName",
        "additionalFirstName","additionalLastName","middleInitial","driversLicense","nationalInsuranceNumber",
        "remarks","mobilePhoneNumber","workPhoneNumber","gender","passport","startDate","redemptionPrivileges",
        "languageId","postOption","numberOfFamilyMembers","anonimity","spouseLastName","memberStatus",
        "receiptLayoutId","adressNormalizationUpdate","updatedDate","companyName","eMailAddress",
        "processingPrevention","receiptType","clubCardId","cards","memberSegments","memberAttributes","memberAdditionalAddress","privacySettings","account"})
@XmlRootElement(name = "Member")
@XmlAccessorType(XmlAccessType.FIELD)
public class Member {
    @XmlAttribute(name = "MemberInternalKey")
    private String memberInternalKey;
    @XmlAttribute(name = "MemberExternalId")
    private String memberExternalId;
    @XmlAttribute(name = "IsMainMember")
    private String isMainMember = "true";
    @XmlAttribute(name = "LastName")
    private String lastName = "Auto_LastName";
    @XmlAttribute(name = "FirstName")
    private String firstName = "Auto_TestFName";
    @XmlAttribute(name = "AdditionalFirstName")
    private String additionalFirstName = "Auto_AddFName";
    @XmlAttribute(name = "AdditionalLastName")
    private String additionalLastName = "Auto_AddLName";
    @XmlAttribute(name = "MiddleInitial")
    private String middleInitial;
    @XmlAttribute(name = "DriversLicense")
    private String driversLicense = "DL123456";
    @XmlAttribute(name = "NationalInsuranceNumber")
    private String nationalInsuranceNumber = "SS1233132";
    @XmlAttribute(name = "Remarks")
    private String remarks ="Auto_Household remarks";
    @XmlAttribute(name = "MobilePhoneNumber")
    private String mobilePhoneNumber = "";
    @XmlAttribute(name = "WorkPhoneNumber")
    private String workPhoneNumber = "";
    @XmlAttribute(name = "Gender")
    private String gender = "0";
    @XmlAttribute(name = "Passport")
    private String passport = "PP123456";
    @XmlAttribute(name = "StartDate")
    private String startDate = GenericMethods.currentDate() +"T00:00:00";
    @XmlAttribute(name = "RedemptionPrivileges")
    private String redemptionPrivileges = "0";
    @XmlAttribute(name = "LanguageId")
    private String languageId = "0";
    @XmlAttribute(name = "PostOption")
    private String postOption = "2";
    @XmlAttribute(name = "NumberOfFamilyMembers")
    private String numberOfFamilyMembers = "1";
    @XmlAttribute(name = "Anonimity")
    private String anonimity = "0";
    @XmlAttribute(name = "SpouseLastName")
    private String spouseLastName = "Auto_SpouseLName";
    @XmlAttribute(name = "MemberStatus")
    private String memberStatus ="1";
    @XmlAttribute(name = "ReceiptLayoutId")
    private String receiptLayoutId ="0";
    @XmlAttribute(name = "AdressNormalizationUpdate")
    private String adressNormalizationUpdate = "";
    @XmlAttribute(name = "UpdatedDate")
    private String updatedDate = GenericMethods.currentDate()+"T00:00:00";
    @XmlAttribute(name = "CompanyName")
    private String companyName = "Allegate";
    @XmlAttribute(name = "EMailAddress")
    private String eMailAddress;
    @XmlAttribute(name = "ProcessingPrevention")
    private String processingPrevention = "false";
    @XmlAttribute(name = "ReceiptType")
    private String receiptType ="0";

    @XmlAttribute(name="ClubCardId")
    private String clubCardId;

    @XmlElement(name="Cards")
    private Cards cards;
    @XmlElement(name = "MemberAttributes")
    private MemberAttributes memberAttributes;
    @XmlElement(name = "MemberAdditionalAddress")
    private MemberAdditionalAddress memberAdditionalAddress;
    @XmlElement(name = "MemberSegments")
    private MemberSegments memberSegments;
    @XmlElement(name = "PrivacySettings")
    private PrivacySettings privacySettings;
    @XmlElement(name="Account")
    private Account account;

    public Member(){}
}
