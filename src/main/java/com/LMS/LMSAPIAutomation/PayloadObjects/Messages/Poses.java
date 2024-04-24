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
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Poses {

    @XmlElement(name="Pos")
    private ArrayList<Pos> pos;
}
