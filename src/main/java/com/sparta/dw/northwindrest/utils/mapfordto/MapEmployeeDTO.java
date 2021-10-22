package com.sparta.dw.northwindrest.utils.mapfordto;

import com.sparta.dw.northwindrest.dtos.EmployeeDTO;
import com.sparta.dw.northwindrest.entities.EmployeeEntity;
import com.sparta.dw.northwindrest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MapEmployeeDTO {


    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees(List<EmployeeEntity> employeeEntityList){
        return  employeeEntityList.stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO convertToEmployeeDTO(EmployeeEntity empEnt){
       EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setCity(empEnt.getCity());
        employeeDTO.setCountry(empEnt.getCountry());
        employeeDTO.setBirthDate(empEnt.getBirthDate());
        employeeDTO.setId(empEnt.getId());
        employeeDTO.setFirstName(empEnt.getFirstName());
        employeeDTO.setHireDate(empEnt.getHireDate());
        employeeDTO.setRegion(empEnt.getRegion());
        employeeDTO.setSalary(empEnt.getSalary());
        employeeDTO.setTitle(empEnt.getTitle());
        employeeDTO.setLastName(empEnt.getLastName());
        employeeDTO.setTitleOfCourtesy(empEnt.getTitleOfCourtesy());

        return employeeDTO;
    }
}
