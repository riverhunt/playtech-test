package org.aut;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestData {
    @JsonProperty("radio_option_comment")
    public String radioComment;
    @JsonProperty("name")
    public String name;
    @JsonProperty("valid_email")
    public String validEmail;
    @JsonProperty("invalid_emails")
    public String[][] invalidEmails;
    @JsonProperty("address")
    public String address;
    @JsonProperty("valid_phone")
    public String validPhone;
    @JsonProperty("invalid_phones")
    public String[][] invalidPhones;
    @JsonProperty("comment")
    public String comment;
}
