package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlType(propOrder = {"noteDateTime","note","subTypeId"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Note {

    @XmlAttribute(name="NoteDateTime")
    private String noteDateTime;
    @XmlAttribute(name="Note")
    private String note = "Test Automation Save Notes";
    @XmlAttribute(name="SubTypeId")
    private String subTypeId;

}
