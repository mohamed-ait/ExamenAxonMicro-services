package org.sid.comptesqrseventsourcing.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequestDTO {
    private String customerId;
    private String nom;
    private String adress;
    private String email;
    private String telephone;
}
