package com.example.demo.repository;

import com.example.demo.entities.Operation;
import com.example.demo.requests.OperationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    //List<Operation> findAll();
    OperationRequest save(OperationRequest request);
    List<Operation> findByTerminal(Integer terminal);
    List<Operation> findByIdLocalAndTerminalAndAndDateTimeBetween(Integer idLocal, Integer terminal, Date startDate, Date endDate);

    //@Query("SELECT o FROM Operation o WHERE o.operation_type = 'start' AND o.terminal = :terminal ORDER BY o.datetime DESC")
    //List<Operation> findMostRecentStartOperation(@Param("terminal") Integer terminal);


}


