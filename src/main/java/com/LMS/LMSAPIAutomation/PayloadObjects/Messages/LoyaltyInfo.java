package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlType(propOrder = {"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action","homeStore","documents","accounts","registeredPromotions"})
@XmlAccessorType(XmlAccessType.FIELD)
public class LoyaltyInfo {

    @XmlAttribute(name = "CardID")
    private String cardID;
    @XmlAttribute(name = "ServerDate")
    private String serverDate;
    @XmlAttribute(name = "MemberEffectiveDate")
    private String memberEffectiveDate;
    @XmlAttribute(name = "CardIDType")
    private String cardIDType;
    @XmlAttribute(name = "Identifier")
    private String identifier;
    @XmlAttribute(name = "LinkUnlinkIdentifier")
    private String linkUnlinkIdentifier;
    @XmlAttribute(name = "Action")
    private String action;
    @XmlElement(name = "HomeStore")
    private String homeStore;
    @XmlElement(name = "Documents")
    private Document documents;
    @XmlElement(name = "Accounts")
    private Accounts accounts;
    @XmlElement(name = "RegisteredPromotions")
    private RegisteredPromotions registeredPromotions;

//    public LoyaltyInfo(){}

}
