package com.example.end.service;


import com.example.end.repository.interfaces.ServiceRepository;
import com.example.end.service.interfaces.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 @Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<com.example.end.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }

}