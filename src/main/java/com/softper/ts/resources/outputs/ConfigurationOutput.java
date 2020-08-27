package com.softper.ts.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConfigurationOutput {
    private String userName;
    private String language;
    private String paymentCurrency;
}
