package org.sid.comptesqrseventsourcing.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategorieRequestDTO {
    private String id;
    private String nom;
    private String description;
    private String categorie;
}
