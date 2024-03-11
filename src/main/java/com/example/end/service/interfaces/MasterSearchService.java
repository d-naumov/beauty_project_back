package com.example.end.service.interfaces;

import com.example.end.entity.Master;

import java.util.List;

public interface MasterSearchService {
    List<Master> searchMastersByCategory(String category);

}