package Portfolio.TabelaFipe.principal;

import Portfolio.TabelaFipe.model.Dados;
import Portfolio.TabelaFipe.model.Modelos;
import Portfolio.TabelaFipe.model.Veiculo;
import Portfolio.TabelaFipe.service.ConsumoApi;
import Portfolio.TabelaFipe.service.ConverteDados;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        var menu = """
                ***Opçãoes de Menu Principal***
                
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar:
                """;

        System.out.println(menu);
        var opcao = leitura.nextLine();

        String endereco;

        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        } else if(opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhões/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(m -> System.out.println("Código: " + m.codigo() + " | Marca: " + m.nome()));

        System.out.println("Informe o codigo do Marca para consultar: ");
        var codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = converteDados.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos()
                .stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do veiculo a ser buscado: ");
        var nomeVeiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos()
                .stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo: ");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);

        List<Dados> anos = converteDados.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for(int i = 0; i < anos.size(); i++){
            var enderecoAno = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAno);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("Todos os veiculos filtrados copm avaliações por ano");
        veiculos.forEach(System.out::println);



    }
}
