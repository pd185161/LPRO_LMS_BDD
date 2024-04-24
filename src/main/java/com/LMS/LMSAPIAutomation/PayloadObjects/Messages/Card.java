package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "Card")
@XmlType(propOrder = {"id","cardStatus","issueDate","expirationDate","barcodeId","cardId","segments"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Card {
    @XmlAttribute(name = "Id")
    private String id;
    @XmlAttribute(name = "CardStatus")
    private String cardStatus = "1";
    @XmlAttribute(name = "IssueDate")
    private String issueDate = GenericMethods.currentDate()+"T00:00:00";
    @XmlAttribute(name = "ExpirationDate")
    private String expirationDate = "2056-01-01T00:00:00";
    @XmlAttribute(name = "BarcodeId")
    private String barcodeId = "5";
    @XmlAttribute(name="CardId")
    private String cardId;

    @XmlElement(name="Segments")
    private Segments segments;

    public Card(){}
}
