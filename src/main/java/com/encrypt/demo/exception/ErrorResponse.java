package com.encrypt.demo.exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  @JsonProperty("RETURNCODE")
  private String returncode;

  @JsonProperty("RETURNDESC")
  private String returndesc;
}
