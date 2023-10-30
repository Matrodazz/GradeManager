package br.com.fiap.grademanager.checkpoint;

import br.com.fiap.grademanager.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Checkpoint {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "O Título não pode estar vazio")
    String titulo;

    @Size(min = 10, message = "{checkpoint.description.size}")
    String descricao;

    @Min(0) @Max(10)
    Integer valor; //valor/nota máxima do checkpoint

    @NotBlank
    String data;

    @ManyToOne
    User user;

}
