package com.LMS.LMSAPIAutomation.PayloadObjects.Messages;
import io.cucumber.java.eo.Se;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement(name = "FilteredAccountDetailedTransactions")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class FilteredAccountDetailedTransactions {

    @XmlElement(name ="Member")
    private Member member;

}
