package com.example.end.service.interfaces;


import com.example.end.models.Role;

public interface RoleService {

  Role findByName(String name);

  Role createRole(String roleName);
}

