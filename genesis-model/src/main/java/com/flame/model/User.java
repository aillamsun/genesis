package com.flame.model;

import com.flame.core.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by sungang on 2017/8/19.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     *
     */
    @Column(name = "user_name")
    private String userName;

    private String password;

    private String email;

    private String remark;

    private Boolean enabled;

    @Transient
    private List<Authority> authorities;

    @Column(name = "last_password_reset_date")
//    @NotNull
    private Date lastPasswordResetDate;

}
