package com.example.end.service.search;

import com.example.end.entity.Master;
import com.example.end.repository.interfaces.MasterRepository;
import com.example.end.service.interfaces.MasterSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterSearchServiceImpl implements MasterSearchService {

    private final MasterRepository masterRepository;

    @Autowired
    public MasterSearchServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public List<Master> searchMastersByCategory(String category) {
        //  поиск мастеров по категории
        return masterRepository.findByCategory(category);
    }




}

