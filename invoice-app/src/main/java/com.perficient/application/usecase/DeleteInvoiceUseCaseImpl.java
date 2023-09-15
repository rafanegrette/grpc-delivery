package usecases;

import ports.in.DeleteTaskUseCase;
import ports.out.TaskRepositoryPort;

public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {
  private final TaskRepositoryPort taskRepositoryPort;

  public DeleteTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
    this.taskRepositoryPort = taskRepositoryPort;
  }

  @Override
  public boolean deleteTask(Long id) {
    return taskRepositoryPort.deleteById(id);
  }
}
