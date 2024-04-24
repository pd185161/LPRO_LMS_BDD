package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"languageId","rowCount","attribute"})
@XmlAccessorType(XmlAccessType.FIELD)

public class Response {

    @XmlAttribute(name = "LanguageId")
    private String languageId;
    @XmlAttribute(name = "RowCount")
    private String rowCount;
    @XmlElement(name = "Attribute")
    private ArrayList<Attribute> attribute;

    public Response(){}

}
