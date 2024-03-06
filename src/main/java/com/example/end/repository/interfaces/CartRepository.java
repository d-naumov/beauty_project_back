package com.example.end.repository.interfaces;

import com.example.end.entity.Cart;
import com.example.end.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    int getId();
    void setId(int id);
    List<Procedure> getProcedure();
    void  setProcedure( List<Procedure> procedures);
    void  addProcedure (Procedure procedure);
    void deleteProcedureById(Long id );
    void  clear();
    double getTotalPrice();
    double getAveragePrice();


    // добавить дополнительные методы

}