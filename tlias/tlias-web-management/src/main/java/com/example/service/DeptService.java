package com.example.service;

import com.example.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    List<Dept> list();

    void delete(Integer id) throws Exception;

    void insert(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
