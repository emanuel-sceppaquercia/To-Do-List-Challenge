package com.example.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Field name should not be null or empty")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    private Boolean finished = false;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folderId;

    public Task(String name){
        this.name = name;
        this.createDate = new Date();
    }

}
