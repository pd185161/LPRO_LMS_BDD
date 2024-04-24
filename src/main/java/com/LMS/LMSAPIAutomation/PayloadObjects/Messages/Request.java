package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {

    @XmlAttribute(name = "Operator")
    private String operator;
    @XmlElement(name = "Attribute")
    private Attribute attribute;

    public Request(){}
}
