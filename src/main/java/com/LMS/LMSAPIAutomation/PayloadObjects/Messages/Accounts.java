package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@XmlType(propOrder = {"account","acc"})
@XmlRootElement(name = "Accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Accounts {

    @XmlElement(name = "Account")
    private Account account;

    @XmlElement(name = "Acc")
    private Account acc;

    public Accounts(){}
}
