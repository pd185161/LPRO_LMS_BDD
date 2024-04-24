package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "Documents")
@XmlType(propOrder = {"msgType","lPEVer","storeID","businessDate","tillID","transactionNumber","externalReferenceId","startDateTime","endDateTime","cashierID","ticketTotal","customerInfo","document"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Documents {

    @XmlAttribute(name = "MsgType")
    private String msgType;
    @XmlAttribute(name = "LPEVer")
    private String lPEVer;
    @XmlAttribute(name = "StoreID")
    private String storeID;
    @XmlAttribute(name = "BusinessDate")
    private String businessDate;
    @XmlAttribute(name = "TillID")
    private String tillID;
    @XmlAttribute(name = "TransactionNumber")
    private String transactionNumber;
    @XmlAttribute(name = "ExternalReferenceId")
    private String externalReferenceId;
    @XmlAttribute(name = "StartDateTime")
    private String startDateTime;
    @XmlAttribute(name = "EndDateTime")
    private String endDateTime;
    @XmlAttribute(name = "CashierID")
    private String cashierID;
    @XmlAttribute(name = "TicketTotal")
    private String ticketTotal;
    @XmlElement(name = "CustomerInfo")
    private CustomerInfo customerInfo;
    @XmlElement(name = "Document")
    private Document document;

    public Documents(){}
}
