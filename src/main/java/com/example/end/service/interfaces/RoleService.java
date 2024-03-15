package com.example.end.service.interfaces;


import com.example.end.models.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

  Role findByName(String name);

  Role createRole(String roleName);
}

