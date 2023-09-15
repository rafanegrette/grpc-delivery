package usecases;

import models.Task;
import ports.in.UpdateTaskUseCase;
import ports.out.TaskRepositoryPort;

import java.util.Optional;

public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {
  private final TaskRepositoryPort taskRepositoryPort;

  public UpdateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
    this.taskRepositoryPort = taskRepositoryPort;
  }

  @Override
  public Optional<Task> updateTask(Long id, Task updateTask) {
    return taskRepositoryPort.update(updateTask);
  }
}
