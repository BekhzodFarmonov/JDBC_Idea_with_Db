package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDto {
 private  String name;
 private  String surname;
 private  String phone;
 private  Integer id;

 public ContactDto(String name, String surname, String phone) {
  this.name = name;
  this.surname = surname;
  this.phone = phone;
 }
}
