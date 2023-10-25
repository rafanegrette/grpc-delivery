package com.perficient.orderapp.configuration.security;

import org.lognet.springboot.grpc.security.GrpcSecurity;
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter;
import org.lognet.springboot.grpc.security.jwt.JwtAuthProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Profile("prod")
@Configuration
public class GrpcSecurityConfiguration extends GrpcSecurityConfigurerAdapter {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public void configure(GrpcSecurity builder) throws Exception {
        builder.authorizeRequests()
                //.methods(CartServiceGrpc.getAddProductMethod()).hasAnyAuthority("SCOPE_profile")
                .and()
                .authenticationProvider(JwtAuthProviderFactory.forAuthorities(jwtDecoder));
    }


}
