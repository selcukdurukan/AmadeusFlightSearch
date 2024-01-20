package com.amadeus.service;

import com.amadeus.dto.request.DoLoginRequestDto;
import com.amadeus.dto.request.RegisterAuthRequestDto;
import com.amadeus.dto.response.DoLoginResponseDto;
import com.amadeus.exception.AuthAppException;
import com.amadeus.exception.ErrorType;
import com.amadeus.mapper.IAuthMapper;
import com.amadeus.repository.IAuthRepository;
import com.amadeus.repository.entity.Auths;
import com.amadeus.utility.JwtTokenManager;
import com.amadeus.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auths, Long> {

    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }


    public void register(RegisterAuthRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRePassword())) {
            throw new AuthAppException(ErrorType.REGISTER_PASSWORD_ERROR);
        }
        if (repository.findOptionalByEmail(dto.getEmail()).isPresent()) {
            throw new AuthAppException(ErrorType.REGISTERED_USER_ERROR);
        }
        save(IAuthMapper.INSTANCE.toAuth(dto));
    }

    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auths> auth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty()) {
            throw new AuthAppException(ErrorType.LOGIN_ERROR);
        }
        Optional<String> token =  jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty())
            throw new AuthAppException(ErrorType.JWT_TOKEN_CREATE_ERROR);
        return token.get();
    }
}
