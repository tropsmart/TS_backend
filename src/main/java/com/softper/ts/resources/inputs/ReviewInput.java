package com.softper.ts.resources.inputs;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewInput {
    private String commentary;
    private double calification;
}
