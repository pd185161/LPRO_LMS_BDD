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
@XmlType(propOrder = {"clubCard","homeStore","clubId"})
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfo {

    @XmlElement(name = "ClubCard")
    private String clubCard;
    @XmlElement(name = "HomeStore")
    private String homeStore;
    @XmlElement(name = "ClubId")
    private String clubId;

    public CustomerInfo(){}
}
