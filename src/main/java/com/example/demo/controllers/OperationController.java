package com.example.demo.controllers;

import com.example.demo.requests.OperationRequest;
import com.example.demo.responses.OperationDto;
import com.example.demo.responses.OperationResponse;
import com.example.demo.services.IOperationService;
import com.example.demo.utils.ApiResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/operation")
@CrossOrigin(origins = "*") // Cambia esto con tus valores
public class OperationController {

    private IOperationService operationSrv;

    public OperationController(IOperationService operationService ){
        this.operationSrv = operationService;
    }
    @Autowired
    private DataSource dataSource;
    @GetMapping("/health")
    public ResponseEntity<Object> checkHealth() {
        try {
            //Revisar conexión a base de datos
            Connection connection = dataSource.getConnection();
            connection.close();
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(true, "Servicio de operaciones ejecutandose correctamente", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error en la comprobación de salud: " + e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(false, "Error en la comprobación de salud: " + e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveOperation")
    public ResponseEntity<Object> saveOperation(@RequestBody OperationRequest operationRequest){
        System.out.println("Request" );
        try {
            operationSrv.save(operationRequest);
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(true, "Creado exitosamente", responseData);
            return new ResponseEntity<>( response  , HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(false, "Error al crear operación: "+e.getMessage(), responseData);
            return new ResponseEntity<>(
                    response,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @GetMapping("/getOperations")
    public ResponseEntity<Object> getOperations(){
        try{
            OperationDto operations = operationSrv.getOperations();
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(true, "Consulta exitosa", operations);
            return new ResponseEntity<>( response  , HttpStatus.OK);

        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(false, "Error al obtener operaciones: "+e.getMessage(), responseData);
            return new ResponseEntity<>(
                    response,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getOperations/{idLocal}/{terminal}/{days}")
    public ResponseEntity<Object> getOperationsByTerminal(@PathVariable(required = true) Integer idLocal, @PathVariable(required = true) Integer terminal,@PathVariable Integer days){
        try{
            int queryDays = 1;
            if(days!=null){
                queryDays = days;
            }

            OperationResponse operationResp  = operationSrv.findByTerminalAndDateTimeBetween(idLocal, terminal, queryDays);
            Map<String, Object> response = ApiResponseGenerator.createResponse(true, "Consulta exitosa", operationResp);
            return new ResponseEntity<>( response  , HttpStatus.OK);

        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            Map<String, Object> response = ApiResponseGenerator.createResponse(false, "Error al obtener operaciones: "+e.getMessage(), null);
            return new ResponseEntity<>(
                    response,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
