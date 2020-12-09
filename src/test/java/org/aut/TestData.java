package org.aut;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestData {
    @JsonProperty("radio_option_comment")
    public String radioComment;
    @JsonProperty("valid_name")
    public String name;
    @JsonProperty("invalid_names")
    public String[][] invalidNames;
    @JsonProperty("valid_email")
    public String email;
    @JsonProperty("invalid_emails")
    public String[][] invalidEmails;
    @JsonProperty("valid_address")
    public String address;
    @JsonProperty("invalid_addresses")
    public String[][] invalidAddresses;
    @JsonProperty("valid_phone")
    public String phone;
    @JsonProperty("invalid_phones")
    public String[][] invalidPhones;
    @JsonProperty("comment")
    public String comment;
}
