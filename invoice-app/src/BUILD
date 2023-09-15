load("@rules_proto//proto:defs.bzl", "proto_library")
proto_library(
    name = "transmission_object_proto",
    srcs = ["transmission_object.proto"],
)
java_proto_library(
    name = "transmission_object_java_proto",
    deps = [":transmission_object_proto"],
)
java_binary(
    name = "echo_client",
    srcs = ["EchoClient.java"],
    main_class = "EchoClient",
    deps = [":transmission_object_java_proto"],
)