package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result getList(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String name, Short gender,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询员工");
        PageBean pageBean = empService.getList(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据id删除数据");
        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("添加员工数据");
        empService.insert(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工");
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息");
        empService.update(emp);
        return Result.success();
    }
}
