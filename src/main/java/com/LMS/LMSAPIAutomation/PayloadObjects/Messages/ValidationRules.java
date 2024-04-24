package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"startDate","endDate","redemptionType","redemptionLocation","redemptionMode"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationRules {

    @XmlElement(name = "StartDate")
    private String startDate;
    @XmlElement(name = "EndDate")
    private String endDate;
    @XmlElement(name = "RedemptionType")
    private String redemptionType;
    @XmlElement(name = "RedemptionLocation")
    private String redemptionLocation;
    @XmlElement(name = "RedemptionMode")
    private String redemptionMode;

    public ValidationRules(){}

}
