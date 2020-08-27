package com.softper.ts.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BlockedOutput {
    private String user;
    private String blocked;
    public Date Since;
}
