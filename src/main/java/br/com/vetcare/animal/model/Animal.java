package br.com.vetcare.animal.model;

import br.com.vetcare.tutor.model.Tutor;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "animal")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 80)
    private String raca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Sexo sexo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Especie especie;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Column(nullable = false, length = 30, unique = true)
    private String rga;
    @Column(nullable = false)
    private boolean vivo = true;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;
}

