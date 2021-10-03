package com.inetum.DTO;



import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoryDto {

    private long id;

    private String name;

    private Timestamp madeDate;

    private Timestamp updateDate;
}
