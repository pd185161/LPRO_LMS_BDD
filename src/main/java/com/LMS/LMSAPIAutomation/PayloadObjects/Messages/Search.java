package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlType(propOrder = {"request","response"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Search {

    @XmlElement(name = "Request")
    private Request request;
    @XmlElement(name = "Response")
    private Response response;

    public Search(){}

}
