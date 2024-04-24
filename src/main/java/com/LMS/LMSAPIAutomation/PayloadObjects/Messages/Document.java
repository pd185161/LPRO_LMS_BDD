package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"documentBarcode","documentType","redemptionType","isUnique","details","validationRules"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

    @XmlAttribute(name = "DocumentBarcode")
    private String documentBarcode;
    @XmlAttribute(name = "DocumentType")
    private String documentType;
    @XmlAttribute(name = "RedemptionType")
    private String redemptionType;
    @XmlAttribute(name = "IsUnique")
    private String isUnique;
    @XmlElement(name = "Details")
    private Details details;
    @XmlElement(name = "ValidationRules")
    private ValidationRules validationRules;

    public Document(){}

}
