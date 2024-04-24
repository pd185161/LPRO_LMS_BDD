package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"id","mode","name","value","dataType"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Attribute {

    @XmlAttribute(name = "Id")
    private String id = "2400";
    @XmlAttribute(name = "Mode")
    private String mode;
    @XmlAttribute(name = "Name")
    private String name;
    @XmlAttribute(name = "Value")
    private String value = "Test";
    @XmlAttribute(name = "DataType")
    private String dataType ="String";

    public Attribute(){}

}
