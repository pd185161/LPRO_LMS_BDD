package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"xmlns_xsi","xsi_noNamespaceSchemaLocation","customer","documents"})
@XmlRootElement(name = "Root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    @XmlAttribute(name = "xmlns:xsi")
    private String xmlns_xsi;
    @XmlAttribute(name = "xsi:noNamespaceSchemaLocation")
    private String xsi_noNamespaceSchemaLocation;
    @XmlElement(name = "Customer")
    private Customer customer;
    @XmlElement(name = "Documents")
    private Documents documents;

    public Root(){}
}
