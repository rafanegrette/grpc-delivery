package usecases;

import models.Task;
import ports.in.CreateTaskUseCase;
import ports.out.TaskRepositoryPort;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {
  private final TaskRepositoryPort taskRepositoryPort;

  public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
    this.taskRepositoryPort = taskRepositoryPort;
  }

  @Override
  public Task createTask(Task task) {
    return taskRepositoryPort.save(task);
  }
}
