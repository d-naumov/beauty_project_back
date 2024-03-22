package com.example.end.service.interfaces;




import com.example.end.dto.NewUserDto;
import com.example.end.dto.UserDto;
import com.example.end.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService  {

      @Transactional
      UserDto register(NewUserDto newUserDto);

    UserDto getById(Long id);

    void confirmMaster(String masterUsername);

    List<UserDto> getAllUsers();

    void deleteById(Long id);


}


