package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfigurationInput {
    private String language;
    private String paymentCurrency;
}
