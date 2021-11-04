package com.encrypt.demo.controller;


import com.encrypt.demo.service.AES256Service;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/aes")
public class AesController {

  /** SHA-1 & AES 256 encrypt */
  @Operation(summary = "SHA-1 & AES 256 encrypt")
  @GetMapping(value = "/encrypt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Map findEncryptString(
      @ApiParam(value = "original string", example = "0000000001", required = true)
          @RequestParam(value = "str", required = true)
          String str,
      @ApiParam(value = "key", example = "0000000001", required = true)
          @RequestParam(value = "key", required = true)
          String key) {

    Map<String, String> map = new HashMap<>();
    map.put("EncryptedString", AES256Service.encrypt(str, key));
    return map;
  }

  /** SHA-1 & AES 256 decrypt */
  @Operation(summary = "SHA-1 & AES 256 decrypt")
  @GetMapping(value = "/decrypt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Map findDecryptString(
      @ApiParam(value = "encrypted string", example = "0000000001", required = true)
          @RequestParam(value = "str", required = true)
          String str,
      @ApiParam(value = "key", example = "0000000001", required = true)
          @RequestParam(value = "key", required = true)
          String key) {

    Map<String, String> map = new HashMap<>();
    map.put("DecryptedString", AES256Service.decrypt(str, key));
    return map;
  }
}
