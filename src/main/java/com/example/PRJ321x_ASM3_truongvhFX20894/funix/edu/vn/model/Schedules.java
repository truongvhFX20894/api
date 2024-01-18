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
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorUsers doctor;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "max_booking")
    private int maxBooking;

    @Column(name = "sum_booking")
    private int sumBooking;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
