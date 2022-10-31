/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crud.test.mapper;

import com.mycompany.crud.test.models.Customer;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 *
 * @author Gurame
 */
public interface CustomerRepository {
    @Select("SELECT * FROM customer")
    public List<Customer> findAll();
    
    @Select("SELECT * FROM customer WHERE id = #{id}")
    public Customer findById(long id);

    @Delete("DELETE FROM customer WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("INSERT INTO customer(id, name, email, address) VALUES (#{id}, #{name}, #{email}, #{address})")
    public int insert(Customer customer);

    @Update("UPDATE customer set name=#{name}, email=#{email}, address=#{address} WHERE id=#{id}")
    public int update(Customer customer);
}
