package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

//    private Logger log = LoggerFactory.getLogger(DeptController.class);

//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list() {
        log.info("查询部门信息");
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    @Log
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门");
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping("/depts")
    public Result insert(@RequestBody Dept dept) {
        log.info("添加部门");
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询部门");
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门信息");
        deptService.update(dept);
        return Result.success();
    }
}
