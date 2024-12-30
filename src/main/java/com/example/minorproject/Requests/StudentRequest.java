package com.example.minorproject.Requests;

import com.example.minorproject.enums.Accountstatus;
import com.example.minorproject.model.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNo;

    private String address;


    public Student toStudent() {
        return  Student.builder().name(name).email(email).accountstatus(Accountstatus.ACTIVE)
                .phoneNo(phoneNo).address(address).build();

    }
}
