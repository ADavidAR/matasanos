package com.matasanos.service;

import com.matasanos.repo.CompraRepo;
import org.springframework.stereotype.Service;


@Service
public class CompraService {
    private final CompraRepo compraRepo;


    public CompraService(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }


}
