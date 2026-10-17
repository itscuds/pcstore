
package br.com.joaovjsilva.store;

import br.com.joaovjsilva.store.model.Computador;
import br.com.joaovjsilva.store.model.Cpu;
import br.com.joaovjsilva.store.model.Fonte;
import br.com.joaovjsilva.store.model.PlacaMae;
import br.com.joaovjsilva.store.model.Ram;
import br.com.joaovjsilva.store.model.Socket;
import br.com.joaovjsilva.store.model.TipoMemoria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoreApplicationTests {

	@Test
	void computadorComecaIncompleto() {
		assertEquals(
				"Computador incompleto",
				new Computador().status()
		);
	}

	@Test
	void computadorCompletoECompativel() {
		Computador computador = new Computador();

		computador.setCpu(new Cpu(
				1L, "CPU", 65, 1000,
				Socket.LGA1151));

		computador.setPlacaMae(new PlacaMae(
				1L, "Placa", 50, 500,
				Socket.LGA1151, TipoMemoria.DDR4));

		computador.setRam(new Ram(
				1L, "RAM", 5, 200,
				TipoMemoria.DDR4, 16));

		computador.setFonte(new Fonte(
				1L, "Fonte", 10, 300, 400));

		assertEquals(
				"Computador completo e funcionando",
				computador.status()
		);

		assertEquals(2000.0, computador.precoTotal());
	}

	@Test
	void identificaIncompatibilidadeDeSocket() {
		Computador computador = new Computador();

		computador.setCpu(new Cpu(
				1L, "CPU", 65, 1000,
				Socket.LGA1200));

		computador.setPlacaMae(new PlacaMae(
				1L, "Placa", 50, 500,
				Socket.LGA1151, TipoMemoria.DDR4));

		computador.setRam(new Ram(
				1L, "RAM", 5, 200,
				TipoMemoria.DDR4, 16));

		computador.setFonte(new Fonte(
				1L, "Fonte", 10, 300, 400));

		assertEquals(
				"Incompatibilidade entre CPU e Placa Mãe",
				computador.status()
		);
	}

	@Test
	void identificaFonteInsuficiente() {
		Computador computador = new Computador();

		computador.setCpu(new Cpu(
				1L, "CPU", 200, 1000,
				Socket.LGA1151));

		computador.setPlacaMae(new PlacaMae(
				1L, "Placa", 100, 500,
				Socket.LGA1151, TipoMemoria.DDR4));

		computador.setRam(new Ram(
				1L, "RAM", 20, 200,
				TipoMemoria.DDR4, 16));

		computador.setFonte(new Fonte(
				1L, "Fonte", 10, 300, 250));

		assertTrue(computador.status().equals(
				"Fonte insuficiente para o sistema"));
	}
}
