package com.spotfinderbackend.iam.infrastructure.hashing.bcrypt;

import com.spotfinderbackend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
