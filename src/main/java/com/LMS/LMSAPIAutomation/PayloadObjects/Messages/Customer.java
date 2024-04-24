package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import io.cucumber.java.eo.Se;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlType(propOrder = {"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID","ticketIdentifier","startDateTime","cashierID","ticketTotal","homeStore","fileSource","QueryMode","locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search","loyaltyInfo"})
@XmlRootElement(name = "Customer")
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @XmlAttribute(name = "TransID")
    private String transID;
    @XmlAttribute(name = "LPEVer")
    private String LPEVer;
    @XmlAttribute(name = "MsgType")
    private String msgType;
    @XmlAttribute(name = "RetailerId")
    private String retailerId;
    @XmlAttribute(name = "BusinessDate")
    private String businessDate;
    @XmlAttribute(name = "PosID")
    private String posID;
    @XmlAttribute(name = "StoreID")
    private String storeID;
    @XmlAttribute(name = "ClientID")
    private String clientID;
    @XmlAttribute(name = "TicketIdentifier")
    private String ticketIdentifier;
    @XmlAttribute(name = "StartDateTime")
    private String startDateTime;
    @XmlAttribute(name = "CashierID")
    private String cashierID;
    @XmlAttribute(name = "TicketTotal")
    private String ticketTotal;
    @XmlAttribute(name = "HomeStore")
    private String homeStore;
    @XmlAttribute(name = "FileSource")
    private String fileSource;
    @XmlAttribute(name = "QueryMode")
    private String QueryMode;
    @XmlAttribute(name = "Locked")
    private String locked;
    @XmlAttribute(name = "xsi:noNamespaceSchemaLocation")
    private String xsi_noNamespaceSchemaLocation;
    @XmlAttribute(name = "xmlns:xsi")
    private String xmlns_xsi;
    @XmlElement(name = "Search")
    private Search search;
    @XmlElement(name = "LoyaltyInfo")
    private LoyaltyInfo loyaltyInfo;

    public Customer(){}

}
