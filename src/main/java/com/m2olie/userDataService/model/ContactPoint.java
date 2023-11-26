package com.m2olie.userDataService.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class ContactPoint {

    @Enumerated(EnumType.STRING)
    private ContactPointSystem system; // e.g., phone, fax, email, pager, url, sms, other
    private String value; // The actual contact point details
    @Enumerated(EnumType.STRING)
    private ContactPointUse use; // home, work, temp, old, mobile - purpose of this contact point
    private Integer rank; // Specify preferred order of use (1 = highest)
    private Period period; // Time period when the contact point was/is in use

    public enum ContactPointSystem {
        PHONE, FAX, EMAIL, PAGER, URL, SMS, OTHER
    }

    public enum ContactPointUse {
        HOME, WORK, TEMP, OLD, MOBILE
    }
}
