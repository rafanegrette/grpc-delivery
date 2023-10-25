FROM gradle:8.4.0-jdk17-alpine as build
WORKDIR /workspace/app

COPY . /workspace/app

RUN apk add gcompat

RUN gradle clean build -x test
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/products-app-infrastructure.jar)

FROM gradle:8.4.0-jdk17-alpine
VOLUME /tmp
WORKDIR /
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.perficient.GrpcDeliveryApplication"]