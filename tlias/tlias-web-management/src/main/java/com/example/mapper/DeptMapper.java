package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门信息
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    /**
     * 添加部门
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID查询部门
     * @param id 部门编号
     * @return 返回部门信息
     */
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 更改部门信息
     * @param dept 传入的部门对象
     */
    @Update("update dept set name = #{name} where id = #{id}")
    void update(Dept dept);
}
