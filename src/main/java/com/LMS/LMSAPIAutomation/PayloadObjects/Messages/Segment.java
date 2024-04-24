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
@XmlType(propOrder = {"segmentInternalKey","id","startDate","endDate","description","attachment","status","segmentId"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Segment {
    @XmlAttribute(name = "SegmentInternalKey")
    private String segmentInternalKey;
    @XmlAttribute(name = "Id")
    private String id;
    @XmlAttribute(name = "StartDate")
    private String startDate = GenericMethods.currentDate()+"T00:00:00";
    @XmlAttribute(name = "EndDate")
    private String endDate = "2056-12-17T09:30:47Z";
    @XmlAttribute(name = "Description")
    private String description;
    @XmlAttribute(name = "Attachment")
    private String attachment = "String";
    @XmlAttribute(name = "Status")
    private String status = "0";
    @XmlAttribute(name="SegmentId")
    private String segmentId;

}
