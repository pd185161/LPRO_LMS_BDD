package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;
import io.cucumber.java.eo.Se;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlType(propOrder = {"storeId","posDateTime","posId","tranId","startDateTime","clubCardId","createdAt","updatedBy","totalAmount","storeName","isTransactionVoid"})
@XmlRootElement(name = "Transaction")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    @XmlAttribute(name="StoreId")
    private String storeId;
    @XmlAttribute(name="PosDateTime")
    private String posDateTime;
    @XmlAttribute(name="PosId")
    private String posId;
    @XmlAttribute(name="TranId")
    private String tranId;
    @XmlAttribute(name="StartDateTime")
    private String startDateTime;
    @XmlAttribute(name="ClubCardId")
    private String clubCardId;
    @XmlAttribute(name="CreatedAt")
    private String createdAt;
    @XmlAttribute(name="UpdatedBy")
    private String updatedBy;
    @XmlAttribute(name="TotalAmount")
    private String totalAmount;
    @XmlAttribute(name="StoreName")
    private String storeName;
    @XmlAttribute(name="IsTransactionVoid")
    private String isTransactionVoid;

}
