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
@Table(name = "extrainfos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Extrainfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @Column(name = "price")
    private int price;

    @Column(name = "history_breath")
    private String historyBreath;

    @Column(name = "old_forms")
    private String oldForms;

    @Column(name = "send_forms")
    private String sendForms;

    @Column(name = "more_info")
    private String moreInfo;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
}
