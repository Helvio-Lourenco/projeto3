package com.lojaDeOroAtelier;

import com.lojaDeOroAtelier.entity.*;
import com.lojaDeOroAtelier.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.lojaDeOroAtelier.entity.PagamentosEntity.MetodoPagamento.DINHEIROAPAGAR;

@SpringBootApplication
public class LojaDeOroAtelierApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaDeOroAtelierApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(AneisRepository aneisRepository, BrincosRepository brincosRepository, ClientesRepository clientesRepository, ColaresRepository colaresRepository, GerentesRepository gerentesRepository, JoiaRepository joiaRepository, PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository, VendedorRepository vendedorRepository){
		return args -> {
			aneisRepository.deleteAll();
			brincosRepository.deleteAll();
			clientesRepository.deleteAll();
			colaresRepository.deleteAll();
			gerentesRepository.deleteAll();
			joiaRepository.deleteAll();
			pagamentoRepository.deleteAll();
			pedidoRepository.deleteAll();
			vendedorRepository.deleteAll();



			JoiaEntity joia = new JoiaEntity("Anel de Ouro", 5.5, 500.0, "Ouro 18k", 10, JoiaEntity.TipoJoia.ANEL);
			joiaRepository.save(joia);

			//ColaresEntity colar = new ColaresEntity("Colar de Diamantes", "Ouro Branco", 10.0, 1200.0, "45cm", 5);
			//colaresRepository.save(colar);

			//AneisEntity anel = new AneisEntity(joia,17.5); // Tamanho do anel
			//aneisRepository.save(anel);

			//BrincosEntity brinco = new BrincosEntity("Brinco de Prata", 3.0, 150.0, "Prata 925", "Fecho Pressão", 20);
			//brincosRepository.save(brinco);

			//ColaresEntity colar = new ColaresEntity("Colar de Diamantes", "Ouro Branco", 10.0, 1200.0, "45cm", 5);
			//colaresRepository.save(colar);

			ClientesEntity helvio = new ClientesEntity(293060800,"helvio","helvio","922039233","rua lios");
			clientesRepository.save(helvio);


			VendedorEntity tiago = new VendedorEntity("tiago",235672947, LocalDate.of(2023, 1, 15),24.52,8);
			vendedorRepository.save(tiago);

			// Criando lista de joias para o pedido
			List<JoiaEntity> itensPedido = List.of(anel, brinco, colar);
			// Calculando valor total do pedido
			double valorTotal = itensPedido.stream().mapToDouble(JoiaEntity::getPreco).sum();

			PedidosEntity pedido1 = new PedidosEntity(LocalDate.of(2023, 1, 15), helvio, itensPedido, PedidosEntity.Status.Pendente, new ArrayList<>(), valorTotal, "DINHEIRO");
			pedidoRepository.save(pedido1);

			PagamentosEntity pagamento = new PagamentosEntity(PagamentosEntity.MetodoPagamento.DINHEIROAPAGAR, LocalDateTime.of(2023, 1, 15, 0, 0), 56.7,pedido1);
			pagamentoRepository.save(pagamento);


		};

	}

}
