package dev.jeron7.todolist.infrastructure.configs;

import dev.jeron7.todolist.application.repositories.TaskRepository;
import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.application.useCases.task.CreateTask;
import dev.jeron7.todolist.application.useCases.task.GetAllTasks;
import dev.jeron7.todolist.application.useCases.task.GetTaskById;
import dev.jeron7.todolist.application.useCases.task.UpdateTaskById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCasesConfig {

    @Bean
    public CreateTask createTask(TaskRepository taskRepository, UserRepository userRepository) {
        return new CreateTask(taskRepository, userRepository);
    }

    @Bean
    public GetTaskById getTaskById(TaskRepository taskRepository) {
        return new GetTaskById(taskRepository);
    }

    @Bean
    public GetAllTasks getAllTasks(TaskRepository taskRepository) {
        return new GetAllTasks(taskRepository);
    }

    @Bean
    public UpdateTaskById updateTaskById(TaskRepository taskRepository, UserRepository userRepository) {
        return new UpdateTaskById(taskRepository, userRepository);
    }
}
