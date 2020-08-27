package com.softper.ts.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FavoriteOutput {
    private String user;
    private String favourited;
    private Date since;
}
