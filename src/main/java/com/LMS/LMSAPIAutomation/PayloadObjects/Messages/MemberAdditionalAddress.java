package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {"country","state","city","street1","postalCode","poBox","remarks"})
@XmlAccessorType(XmlAccessType.FIELD)
public class MemberAdditionalAddress {

    @XmlAttribute(name = "Country")
    private String country = "100";
    @XmlAttribute(name = "State")
    private String state = "10";
    @XmlAttribute(name = "City")
    private String city = "Second City";
    @XmlAttribute(name = "Street1")
    private String street1 = "Additional Street1";
    @XmlAttribute(name = "PostalCode")
    private String postalCode = "7875";
    @XmlAttribute(name = "POBox")
    private String poBox = "sec PO";
    @XmlAttribute(name = "Remarks")
    private String remarks = "Second details";

}
