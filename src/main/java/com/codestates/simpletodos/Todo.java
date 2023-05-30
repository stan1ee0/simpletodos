package com.codestates.simpletodos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "todoOrder", nullable = false)
    private int order;
    private boolean completed;

    public Todo(TodoDto todoDto) {
        this.title = todoDto.getTitle();
        this.order = todoDto.getOrder();
        this.completed = todoDto.isCompleted();
    }
}
