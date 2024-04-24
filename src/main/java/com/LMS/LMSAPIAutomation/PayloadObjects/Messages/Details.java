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
@XmlType(propOrder = {"action","barcode","iD","barcodeProgrammingId","isUnique","uniqueID","triggerPromotionId","value","qty","tenderGroup","type"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Details {

    @XmlElement(name = "Action")
    private String action;
    @XmlElement(name = "Barcode")
    private String barcode;
    @XmlElement(name = "ID")
    private String iD;
    @XmlElement(name = "BarcodeProgrammingId")
    private String barcodeProgrammingId;
    @XmlElement(name = "IsUnique")
    private String isUnique;
    @XmlElement(name = "UniqueID")
    private String uniqueID;
    @XmlElement(name = "TriggerPromotionId")
    private String triggerPromotionId;
    @XmlElement(name = "Value")
    private String value;
    @XmlElement(name = "Qty")
    private String qty;
    @XmlElement(name = "TenderGroup")
    private String tenderGroup;
    @XmlElement(name = "Type")
    private String type;

    public Details(){}

}
