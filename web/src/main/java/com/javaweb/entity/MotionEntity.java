package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "motion")
@Getter
@Setter
public class MotionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="time")
    private Date time;
    @Column(name="motiontype")
    private String motionType;
    @Column(name = "image")
    private String image;
}
