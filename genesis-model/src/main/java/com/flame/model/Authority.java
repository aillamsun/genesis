package com.flame.model;

import com.flame.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by sungang on 2017/8/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "auth")
public class Authority extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_key", length = 50)
    private String appKey;

    @Column(name = "secrity_key", length = 50)
    private String secrityKey;

    private String token;
}
