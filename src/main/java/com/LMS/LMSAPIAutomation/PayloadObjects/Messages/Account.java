package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@XmlType(propOrder = {"ID","Ids","earnValue","rdmValue","openBalance","initialValue","transactionStartDateTime"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @XmlAttribute(name = "ID")
    private String ID;
    @XmlAttribute(name = "Id")
    private String Ids;
    @XmlAttribute(name = "EarnValue")
    private String earnValue;
    @XmlAttribute(name = "RdmValue")
    private String rdmValue;
    @XmlAttribute(name = "OpenBalance")
    private String openBalance;
    @XmlAttribute(name = "InitialValue")
    private String initialValue;
    @XmlAttribute(name = "TransactionStartDateTime")
    private String transactionStartDateTime;

    public Account(){}

}
