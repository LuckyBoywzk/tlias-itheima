package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 分页查询
     * @param start 起始索引
     * @param pageSize 查询返回记录数
     * @return
     */
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    List<Emp> getList(Integer start, Integer pageSize);

    /**
     * c查询总记录数
     */

//    @Select("select count(*) from emp")
//    Long count();

    /**
     * PageHelp实现分页查询
     */
    List<Emp> getList(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据ID删除数据
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values " +
            "(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id查询员工
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 修改员工信息
     */
    void update(Emp emp);

    /**
     * 根据用户名和密码进项校验
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);

    /**
     * 根据部门ID删除信息
     */
    @Delete("delete from emp where dept_id = #{deptId};")
    void deleteByDeptId(Integer deptId);
}
