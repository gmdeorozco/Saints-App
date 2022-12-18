package com.saintsapp.saintsserver.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReligiousOrdersEntitiesList {
    private List<ReligiousOrderEntity> religiousOrderEntities = new ArrayList<>();
}
