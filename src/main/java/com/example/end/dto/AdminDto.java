package com.example.end.dto;

import lombok.Data;


@Data
public class AdminDto {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;



  public AdminDto() {
  }

  public AdminDto(Long id, String firstName, String lastName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }


}

