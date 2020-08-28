package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfigurationInput {
    private String firstName;
    private String lastName;
    private String phone;
    private String language;
    private String paymentCurrency;
}
