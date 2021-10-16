package com.softper.ts.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class DataUtils {

  private Dataobj data;
  
  public DataUtils() throws JsonParseException, JsonMappingException, IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    data = objectMapper.readValue(new File("ExtraDataUtil.json"), Dataobj.class);
    if(data == null)
      System.out.println("is null");
    else
      System.out.println("is not nsull");
  }
  
}
