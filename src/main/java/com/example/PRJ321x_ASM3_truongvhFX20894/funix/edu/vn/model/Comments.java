package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorUsers doctor;

    @Column(name = "price")
    private int price;

    @Column(name = "time_booking")
    private Time timeBooking;

    @Column(name = "date_booking")
    private Date dateBooking;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
