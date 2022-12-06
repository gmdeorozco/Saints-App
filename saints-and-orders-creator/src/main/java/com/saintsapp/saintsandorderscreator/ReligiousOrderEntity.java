package com.saintsapp.saintsandorderscreator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReligiousOrderEntity implements Serializable{

    private long id;
    private String religiousOrderName;
    private LocalDate religiousOrderFoundationDate;
    private List<SaintEntity> saintsOnOrder;
    private SaintEntity orderFounder;
}
