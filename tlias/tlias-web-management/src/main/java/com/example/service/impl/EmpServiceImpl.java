package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
/*    @Override
    public PageBean getList(Integer page, Integer pageSize) {
        List<Emp> list = empMapper.getList((page - 1) * pageSize, pageSize);
        Long total = empMapper.count();
        PageBean pageBean = new PageBean(list, total);
        return pageBean;
    }*/

    /**
     * pageHelp插件实现查询
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getList(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> list = empMapper.getList(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;
        PageBean pageBean = new PageBean(p.getResult(), p.getTotal());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        System.out.println(emp.getUpdateTime());
        empMapper.update(emp);
    }

    @Override
    public Emp getUsernameAndPassword(Emp emp) {
        Emp e = empMapper.getUsernameAndPassword(emp);
        return e;
    }
}
