package com.scaffold.model;

import com.scaffold.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sungang on 2017/8/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "authority")
public class Authority extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    @NotNull
    private String name;


}
