package com.amadeus.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {

    @CreatedDate
    @Field(write = Field.Write.NON_NULL, targetType = FieldType.TIMESTAMP)
    @JsonFormat(pattern="dd/MM/yyyy - HH:mm:ss")
    private Date createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    @Field(targetType = FieldType.TIMESTAMP)
    @JsonFormat(pattern="dd/MM/yyyy - HH:mm:ss")
    private Date updatedAt;
    @LastModifiedBy
    private String updatedBy;
}
