package com.example.demo.services;

import com.example.demo.requests.OperationRequest;
import com.example.demo.responses.OperationDto;
import com.example.demo.responses.OperationResponse;

import java.util.List;

public interface


IOperationService {
    OperationRequest save(OperationRequest request);

    OperationDto getOperations();

    List<OperationDto> getOperationsByTerminal(Integer terminal );

    OperationResponse findByTerminalAndDateTimeBetween(Integer idLocal, Integer terminal, Integer days );

}
