package packages.projetodae.dtos;

import packages.projetodae.entities.Encomenda;

import java.util.LinkedList;
import java.util.List;

public class FabricanteDTO {
    public String nome;
    public LinkedList<OperadorDeLogistica> operadoresDeLogistica;

    public LinkedList<Encomenda> encomendas;

    public LinkedList<ConsumidorFinal> consumidoresFinais;
}
