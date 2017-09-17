package com.scaffold.model;

import com.scaffold.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by sungang on 2017/8/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user_authority")
public class UserAuthorty extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "authority_id")
    private Long authorityId;
}
