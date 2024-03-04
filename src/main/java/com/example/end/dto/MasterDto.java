package com.example.end.dto;

import lombok.Data;

@Data
public class MasterDto {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String specialization;


  public MasterDto() {
  }

  public MasterDto(Long id, String firstName, String lastName, String email, String phoneNumber, String specialization) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.specialization = specialization;
  }


}
