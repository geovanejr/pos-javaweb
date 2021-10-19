package br.com.geovanejunior.espjava.crudcidades.visao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public final class Cidade {

    @NotBlank(message = "{app.cidade.blank}")
    @Size(min=5, max=60, message = "{app.cidade.size}")
    private final String nome;

    @NotBlank(message = "app.estado.blank")
    @Size(min=2, max=2, message = "{app.estado.size}")
    private final String estado;


}
