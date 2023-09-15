package usecases;

import models.Task;
import ports.in.RetriveTaskUseCase;
import ports.out.TaskRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetriveTaskUseCaseImpl implements RetriveTaskUseCase {
  private final TaskRepositoryPort taskRepositoryPort;

  public RetriveTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
    this.taskRepositoryPort = taskRepositoryPort;
  }

  @Override
  public Optional<Task> getTask(Long id) {
    return taskRepositoryPort.findById(id);
  }

  @Override
  public List<Task> getAllTasks() {
    return taskRepositoryPort.findAll();
  }
}
