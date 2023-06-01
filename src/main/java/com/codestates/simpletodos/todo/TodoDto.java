package com.codestates.simpletodos.todo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Data
public class TodoDto {
    private long id;
    @NotBlank(message = "공백 문자가 아니어야 합니다")
    private String title;
    @PositiveOrZero(message = "음수가 아니어야 합니다")
    private int order;
    private boolean completed;
    private String url;

    public TodoDto(Todo todo) {
        id = todo.getId();
        title = todo.getTitle();
        order = todo.getOrder();
        completed = todo.isCompleted();
    }

    public TodoDto(Todo todo, String url) {
        this(todo);
        this.url = url;
    }
}
