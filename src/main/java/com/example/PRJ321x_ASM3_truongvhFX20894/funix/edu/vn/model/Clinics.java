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
@Table(name = "clinics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "introduceHTML")
    private String introduceHTML;

    @Column(name = "introduce_markdown")
    private String introduceMarkdown;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "create_at")
    @CreationTimestamp
    private Date createAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id")
    private List<DoctorUsers> doctors;
}
