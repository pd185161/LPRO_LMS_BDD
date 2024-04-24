package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"promotionHeaderId","numOfRedemptions"})
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisteredPromotion {

    @XmlAttribute(name = "PromotionHeaderId")
    private String promotionHeaderId;
    @XmlAttribute(name = "NumOfRedemptions")
    private String numOfRedemptions;

    public RegisteredPromotion(){}

}
