package com.example.pbl.service;

import com.example.pbl.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {
    @Autowired
    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }
    public List<String> getProvince(){
        return localRepository.listProvinces();
    }
    public List<String> getDistrict(String province){
        return localRepository.listDistrictsByProvince(province);
    }
    public List<String> getWard(String pro,String dis){
        return localRepository.listWard(pro,dis);
    }
}
