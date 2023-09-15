package usecases;

import models.AdditionalTaskInfo;
import ports.in.GetAdditionalTaskInfoUseCase;
import ports.out.ExternalServicePort;
import ports.out.TaskRepositoryPort;

public class GetAdditionaUseCaseImpl implements GetAdditionalTaskInfoUseCase {
  private final ExternalServicePort externalServicePort;

  public GetAdditionaUseCaseImpl(ExternalServicePort externalServicePort) {
    this.externalServicePort = externalServicePort;
  }

  @Override
  public AdditionalTaskInfo getAdditionalTaskInfo(Long id) {
    return externalServicePort.getAdditionalTaskInfo(id);
  }
}
