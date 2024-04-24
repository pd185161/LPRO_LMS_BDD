package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;
import io.cucumber.java.eo.Se;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@Getter
@Setter
@XmlType(propOrder = {"maxRows","manualTransactionsOnly","posTransactionsOnly","startDate","endDate","poses"})
@XmlRootElement(name = "Filter")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Filter {
    @XmlAttribute (name="MaxRows")
    private String maxRows;
    @XmlAttribute (name="ManualTransactionsOnly")
    private String manualTransactionsOnly;
    @XmlAttribute (name="PosTransactionsOnly")
    private String posTransactionsOnly;
    @XmlAttribute (name="StartDate")
    private String startDate;
    @XmlAttribute (name="EndDate")
    private String endDate;

    @XmlElement(name="Poses")
    private Poses poses;

}
