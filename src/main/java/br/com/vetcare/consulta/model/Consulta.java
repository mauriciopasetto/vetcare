package br.com.vetcare.consulta.model;

import br.com.vetcare.animal.model.Animal;
import br.com.vetcare.veterinario.model.Veterinario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "consulta")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @Column(nullable = false)
    private LocalTime horaConsulta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;

    @Column(columnDefinition = "TEXT")
    private String historico;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataMarcacao; // Quando a consulta foi registrada
}