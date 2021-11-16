package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "folder")
@AllArgsConstructor
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Field name should not be null or empty")
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Task> tasks;

}
