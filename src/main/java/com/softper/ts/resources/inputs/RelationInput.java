package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationInput {
    public int userFrom;
    public int userTo;
}
