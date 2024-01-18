package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "doctor_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Users doctors;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinics clinics;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specializations specializations;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "intro")
    private String intro;

    @Column(name = "train_process")
    private String trainProcess;

    @Column(name = "achievement")
    private String achievement;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Comments> comments;
}
