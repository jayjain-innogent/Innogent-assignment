package com.example.students.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "STUDENT")
@Table
@Data
public class Student {
    @Id
    int id;
    String name;
    String email;

}
