package com.sparta.dw.northwindrest.utils.mapfordto;

import com.sparta.dw.northwindrest.dtos.EmployeeDTO;
import com.sparta.dw.northwindrest.dtos.EmployeeDTO2;
import com.sparta.dw.northwindrest.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MapEmployeeDTO {

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
        employeeDTO.setSalary(empEnt.getSalary());
        employeeDTO.setTitle(empEnt.getTitle());
        employeeDTO.setLastName(empEnt.getLastName());
        employeeDTO.setTitleOfCourtesy(empEnt.getTitleOfCourtesy());

        return employeeDTO;
    }

    public List<EmployeeDTO2> getAllEmployees2(List<EmployeeEntity> employeeEntityList){
        return  employeeEntityList.stream()
                .map(this::convertToEmployeeDTO2)
                .collect(Collectors.toList());
    }

    public EmployeeDTO2 convertToEmployeeDTO2(EmployeeEntity empEnt){
        EmployeeDTO2 employeeDTO2 = new EmployeeDTO2();

        employeeDTO2.setCountry(empEnt.getCountry());
        employeeDTO2.setId(empEnt.getId());
        employeeDTO2.setName(empEnt.getFirstName(),empEnt.getLastName(), empEnt.getTitleOfCourtesy());
        employeeDTO2.setTitle(empEnt.getTitle());

        return employeeDTO2;
    }
}
