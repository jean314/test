package com.example.demo.services.impl;

import com.example.demo.entities.Operation;
import com.example.demo.entities.OperationType;
import com.example.demo.repository.OperationRepository;
import com.example.demo.requests.OperationRequest;
import com.example.demo.responses.OperationDto;
import com.example.demo.responses.OperationResponse;
import com.example.demo.services.IOperationService;
import com.example.demo.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements IOperationService {

  private OperationRepository operationRepository;
  private ModelMapper mapper;

  public OperationServiceImpl(OperationRepository operationRepository, ModelMapper mapper){
    this.operationRepository = operationRepository;
    this.mapper = mapper;
  }


  @Override
  public OperationRequest save(OperationRequest request) {
    Operation operation = mapToEntity(request);
    operationRepository.save(operation);
    System.out.println( "Esto se guardara: "+ operation.getDetails());
    return request;
  }

  @Override
  public OperationDto getOperations(){
    List<Operation> operations = operationRepository.findAll();
    OperationDto response = new OperationDto();
    return response;
  }

  @Override
  public List<OperationDto> getOperationsByTerminal(Integer terminal) {
    List<Operation> operations = operationRepository.findByTerminal(terminal);
    return operations.stream().map( (op)-> mapToOperationDto(op))
            .collect(Collectors.toList());
  }

  @Override
  public OperationResponse findByTerminalAndDateTimeBetween(Integer idLocal, Integer terminal, Integer days) {

    Date[] range = DateUtils.getStartAndDateDatesFromNumberOfDays(days);
    Date start = range[0];
    Date end = range[1];
    List<Operation> operationsFromRepository = operationRepository.findByIdLocalAndTerminalAndAndDateTimeBetween(idLocal, terminal, start, end);
    Long totalOperations = operationsFromRepository.stream().count();

    //BALANCES
    double cashBalance = 0.0f;
    double cardBalance = 0.0f;
    double smxCardBalance = 0.0f;

    List<Operation> saleOperations = new ArrayList<>();

       saleOperations = operationsFromRepository.stream()
              .filter(operation -> operation.getDateTime().toInstant().atZone
                      (java.time.ZoneId.systemDefault()).toLocalDate().isEqual(
                              LocalDateTime.now().toLocalDate()))
              .filter(operation -> operation.getOperationType() == OperationType.sale)
              .collect(Collectors.toList());


    cashBalance = saleOperations.stream()
            .mapToDouble(operation -> operation.getCashPayment() - operation.getChange())
            .sum();

    cardBalance = saleOperations.stream()
            .mapToDouble(Operation::getCreditOrDebitCardPayment)
            .sum();

    smxCardBalance = saleOperations.stream()
            .mapToDouble(Operation::getSmxCardPayment)
            .sum();

    BigDecimal bd = new BigDecimal(cashBalance);
    BigDecimal redondeado = bd.setScale(2, RoundingMode.HALF_UP);
    cashBalance = redondeado.doubleValue();
    bd = new BigDecimal(cardBalance);
    redondeado = bd.setScale(2, RoundingMode.HALF_UP);
    cardBalance = redondeado.doubleValue();
    bd = new BigDecimal(smxCardBalance);
    redondeado = bd.setScale(2, RoundingMode.HALF_UP);
    smxCardBalance = redondeado.doubleValue();




    double totalBalance = smxCardBalance + cardBalance + cashBalance;

    List<OperationDto> operationList = operationsFromRepository.stream().map((op)->mapToOperationDto(op) )
            .collect(Collectors.toList());

    OperationResponse response = new OperationResponse();
    response.setOperations(operationList);
    response.setCashBalance( cashBalance );
    response.setCreditOrDebitCardBalance(cardBalance);
    response.setTotalBalance(totalBalance);
    response.setTotalOperations(totalOperations.intValue());
    response.setSmxCardBalance( smxCardBalance );
    response.setEndQuery(end);
    response.setStartQuery(start);
    return response;
  }

  private OperationDto mapToOperationDto(Operation operation){
      return mapper.map(operation, OperationDto.class);
  }

  private Operation mapToEntity(OperationRequest request){
    String details = request.getDetailsToString().trim();
    System.out.println("Este es el details del payload:  "+details);
    System.out.println("obj:  "+request);

    Operation operation = new Operation(
            request.getOperationNumber(),
            request.getIdLocal(),
            request.getTerminal(),
            request.getOperationType(),
            request.getTotalAmount(),
            request.getCashPayment(),
            request.getCreditOrDebitCardPayment(),
            request.getSmxCardPayment(),
            request.getChange(),
            request.getProccessedCorrectly(),
            details,
            request.getGeneratedSxaracFileNumber()
    );

    System.out.println("Mapeado de request a clase: "+ operation.getDetails());
    return operation;
  }

}
