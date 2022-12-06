package com.saintsapp.saintsandorderscreator;

import java.io.Serializable;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaintEntity implements Serializable {

    private Long id;

    private String saintName;
    private String saintQuote;
    private String saintPlaceOfBirth;
    private boolean saintIsApostle;
    private ReligiousOrderEntity saintReligiousOrder;
    private ReligiousOrderEntity orderFoundedBySaint;
   

}
