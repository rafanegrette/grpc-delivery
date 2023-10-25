package com.perficient.orderapp.ingrastructure.adapter.in.grpc;

import com.perficient.orderapp.configuration.security.GrpcSecurityConfiguration;
import com.perficient.orderapp.configuration.security.JwtConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@TestConfiguration
public class SecurityConfiguration {
    @MockBean
    GrpcSecurityConfiguration grpcSecurityConfiguration;
    @MockBean
    JwtConfiguration jwtConfiguration;
    @MockBean
    private JwtDecoder jwtDecoder;
}
