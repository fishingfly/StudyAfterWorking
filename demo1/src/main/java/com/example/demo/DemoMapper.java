package com.example.demo;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface DemoMapper {
    
    //#{name}:参数占位符
    @Select("select *from Demo1 where name=#{name}")
    public List<Demo> likeName(String name);
    
    
    @Select("select *from Demo1 where id = #{id}")
    public Demo getById(long id);
    
    @Select("select name from Demo1 where id = #{id}")
    public String getNameById(long id);

    
    /**
     * 保存数据.
     */
    @Insert("insert into Demo1(name) values(#{name})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    public void save(Demo demo);
    
}