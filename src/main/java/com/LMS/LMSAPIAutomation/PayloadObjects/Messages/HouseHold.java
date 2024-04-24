package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;


import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlType(propOrder = {"buyingUnitInternalKey","houseHoldExternalId","country","state","city","street1","street2","postalCode","phonePrefix","homePhone","sendEmail","county","houseName","members"})
@XmlRootElement(name = "HouseHold")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)

public class HouseHold {
    @XmlAttribute(name = "BuyingUnitInternalKey")
    private String buyingUnitInternalKey = "0";
    @XmlAttribute(name = "HouseHoldExternalId")
    private String houseHoldExternalId;
    @XmlAttribute(name = "Country")
    private String country = "100";
    @XmlAttribute(name = "State")
    private String state = "10";
    @XmlAttribute(name = "City")
    private String city = "HCity";
    @XmlAttribute(name = "Street1")
    private String street1 = "Sreet Name";
    @XmlAttribute(name = "Street2")
    private String street2 = "Street Name2";
    @XmlAttribute(name = "PostalCode")
    private String postalCode = "7874";
    @XmlAttribute(name = "PhonePrefix")
    private String phonePrefix = "+1";
    @XmlAttribute(name = "HomePhone")
    private String homePhone = "";
    @XmlAttribute(name = "SendEmail")
    private String sendEmail = "false";
    @XmlAttribute(name = "County")
    private String county = "";
    @XmlAttribute(name = "HouseName")
    private String houseName = "";
    @XmlElement(name = "Members")
    private Members members;

}
