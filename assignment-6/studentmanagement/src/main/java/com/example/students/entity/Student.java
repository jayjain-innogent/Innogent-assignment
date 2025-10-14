package com.example.students.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity(name = "STUDENT")
@Table
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

}
