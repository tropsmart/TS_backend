package com.softper.ts.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dataobj implements Serializable {

  @Column(name="vehicles", nullable = false)
  private List<String> vehicles = new ArrayList<>();
    
  @Column(name="locations", nullable = false)
  private List<String> locations = new ArrayList<>();

}
