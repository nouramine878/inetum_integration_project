package com.inetum.DTO;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDto {

    private long id;

    private String name;

    private String quantity;

    private boolean disponibility;

    private Timestamp madeDate;


    private Timestamp updateDate;
}
