import java.util.List;
import java.util.Scanner;

public class Teste extends BDVeiculos {

    // private final static Passeio passeio = new Passeio();
    // private final static Carga carga = new Carga();

    private final static BDVeiculos bdVeiculos = new BDVeiculos();

    private final static Leitura l = new Leitura();

    // private static int indicePasseio = 0;
    private static int indiceCarga = 0;

    public static void main(String[] args) {
        Teste teste = new Teste();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n============================================");
            System.out.println("\nSistema de Gestao de Veiculos - Menu Inicial \n");
            System.out.println("============================================\n");
            System.out.println("1) Cadastrar Veículo de Passeio");
            System.out.println("2) Cadastrar Veículo de Carga");
            System.out.println("3) Imprimir Todos os Veiculos de Passeio");
            System.out.println("4) Imprimir Todos os Veiculos de Carga");
            System.out.println("5) Imprimir Veículo de Passeio pela Placa");
            System.out.println("6) Imprimir Veículo de Carga pela Placa");
            System.out.println("7) Excluir Veiculo de Passeio pela Placa");
            System.out.println("8) Excluir Veiculo de Carga pela Placa");
            System.out.println("9) Sair do Sistema");
            System.out.print("\nEscolha uma opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    teste.cadastrarPasseio();
                    break;
                case 2:
                    teste.cadastrarCarga();
                    break;
                case 3:
                    teste.imprimirTodosPasseio();
                    break;
                case 4:
                    teste.imprimirTodosCarga();
                    break;
                case 5:
                    teste.imprimirPasseioPorPlaca();
                    break;
                case 6:
                    teste.imprimirCargaPorPlaca();
                    break;
                case 7:
                    teste.excluirPasseioPorPlaca();
                    break;
                case 8:
                    teste.excluirCargaPorPlaca();
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        } while (opcao != 9);

        scanner.close();
    }

    private float verificarVelocidadeMaxima(float velocMax, String tipoVeiculo) {
        try {
            if (velocMax < 80 || velocMax > 110) {
                throw new VelocException();
            }
            return velocMax;
        } catch (VelocException e) {
            System.out.println(e.getMessage());
            if (tipoVeiculo.equalsIgnoreCase("passeio")) {
                System.out.println("Velocidade máxima ajustada para 100 km/h (passeio).");
                return 100;
            } else if (tipoVeiculo.equalsIgnoreCase("carga")) {
                System.out.println("Velocidade máxima ajustada para 90 km/h (carga).");
                return 90;
            }
            return velocMax;
        }
    }

    public void cadastrarPasseio() {
        do {
            try {
                String placa = l.entDados("\nDigite a placa do veiculo de passeio: ");

                if (bdVeiculos.placaExiste(placa, "passeio")) {
                    throw new VeicExistException(placa);
                } else {

                // if (indicePasseio < bdVeiculos.getListaPasseio().size()) {
                    Passeio veiculoPasseio = new Passeio();

                    veiculoPasseio.setPlaca(placa);
                    veiculoPasseio.setMarca(l.entDados("Digite a marca do veiculo de passeio: "));
                    veiculoPasseio.setModelo(l.entDados("Digite o modelo do veiculo de passeio: "));
                    veiculoPasseio.setCor(l.entDados("Digite a cor do veiculo de passeio: "));
                    veiculoPasseio.setVelocMax(verificarVelocidadeMaxima(
                            Float.parseFloat(l.entDados("Digite a velocidade maxima do veiculo de passeio: ")),
                            "passeio"));
                    veiculoPasseio.setQtdRodas(
                            Integer.parseInt(l.entDados("Digite a quantidade de rodas do veiculo de passeio: ")));
                    veiculoPasseio.setQtdPassageiros(
                            Integer.parseInt(l.entDados("Digite a quantidade de passageiros do veiculo de passeio: ")));
                    veiculoPasseio.getMotor().setQtdPist(Integer.parseInt(l.entDados("Qtd Pistoes do motor: ")));
                    veiculoPasseio.getMotor().setPotencia(Integer.parseInt(l.entDados("Potencia do motor: ")));

                    // bdVeiculos.getListaPasseio()[indicePasseio] = veiculoPasseio;
                    bdVeiculos.getListaPasseio().add(veiculoPasseio);
                    // indicePasseio++;

                    System.out.println("===== Veiculo de Passeio Cadastrado =====");
                    System.out.println("\nPlaca := " + veiculoPasseio.getPlaca());
                    System.out.println("Marca := " + veiculoPasseio.getMarca());
                    System.out.println("Modelo := " + veiculoPasseio.getModelo());
                    System.out.println("Cor := " + veiculoPasseio.getCor());
                    System.out.println("Velocidade Maxima (km/h) := " + veiculoPasseio.getVelocMax());
                    System.out.println("Quantidade de rodas := " + veiculoPasseio.getQtdRodas());
                    System.out.println("Qtd Passageiros := " + veiculoPasseio.getQtdPassageiros());
                    System.out.println("Qtd Pistoes Motor := " + veiculoPasseio.getMotor().getQtdPist());
                    System.out.println("Potencia do Motor := " + veiculoPasseio.getMotor().getPotencia());

                    System.out.println("\nVeiculo de passeio cadastrado com sucesso!");
                }
                    // } else {
                //     System.out.println("Não é possível cadastrar mais veículos de passeio. O vetor está cheio.");
                //     return;
                // }

                String resposta = l.entDados("Deseja cadastrar mais um veiculo de passeio? (s/n): ");
                if (resposta.equalsIgnoreCase("n")) {
                    System.out.println("Voltando ao menu inicial.");
                    return;
                }

            } catch (VeicExistException e) {
                System.out.println(e.getMessage());
                System.out.println("Voltando ao menu inicial.");
                return;
            }
        } while (true);
    }

    public void cadastrarCarga() {
        do {
            try {
                String placa = l.entDados("\nDigite a placa do veiculo de carga: ");

                if (bdVeiculos.placaExiste(placa, "carga")) {
                    throw new VeicExistException(placa);
                } else {

                // if (indiceCarga < bdVeiculos.getListaCarga().size()) {
                    Carga veiculoCarga = new Carga();

                    veiculoCarga.setPlaca(placa);
                    veiculoCarga.setMarca(l.entDados("Digite a marca do veiculo de carga: "));
                    veiculoCarga.setModelo(l.entDados("Digite o modelo do veiculo de carga: "));
                    veiculoCarga.setCor(l.entDados("Digite a cor do veiculo de carga: "));
                    veiculoCarga.setVelocMax(verificarVelocidadeMaxima(
                            Float.parseFloat(l.entDados("Digite a velocidade maxima do veiculo de carga: ")), "carga"));
                    veiculoCarga.setQtdRodas(
                            Integer.parseInt(l.entDados("Digite a quantidade de rodas do veiculo de carga: ")));
                    veiculoCarga
                            .setCargaMax(Integer.parseInt(l.entDados("Digite a carga maxima do veiculo de carga: ")));
                    veiculoCarga.setTara(Integer.parseInt(l.entDados("Digite a tara do veiculo de carga: ")));
                    veiculoCarga.getMotor().setQtdPist(Integer.parseInt(l.entDados("Qtd Pistoes do motor: ")));
                    veiculoCarga.getMotor().setPotencia(Integer.parseInt(l.entDados("Potencia do motor: ")));

                    // bdVeiculos.getListaCarga()[indiceCarga] = veiculoCarga;
                    bdVeiculos.getListaCarga().add(veiculoCarga);
                    indiceCarga++;

                    System.out.println("\n===== Veiculo de Carga Cadastrado =====");
                    System.out.println("\nPlaca := " + veiculoCarga.getPlaca());
                    System.out.println("Marca := " + veiculoCarga.getMarca());
                    System.out.println("Modelo := " + veiculoCarga.getModelo());
                    System.out.println("Cor := " + veiculoCarga.getCor());
                    System.out.println("Velocidade Maxima (km/h) := " + veiculoCarga.getVelocMax());
                    System.out.println("Quantidade de Rodas: " + veiculoCarga.getQtdRodas());
                    System.out.println("Carga Maxima := " + veiculoCarga.getCargaMax());
                    System.out.println("Tara := " + veiculoCarga.getTara());
                    System.out.println("Qtd Pistoes Motor := " + veiculoCarga.getMotor().getQtdPist());
                    System.out.println("Potencia do Motor := " + veiculoCarga.getMotor().getPotencia());

                    System.out.println("\nVeiculo de Carga cadastrado com sucesso!");
                }
                // } else {
                //     System.out.println("Não é possível cadastrar mais veículos de carga. O vetor está cheio.");
                //     return;
                // }

                String resposta = l.entDados("Deseja cadastrar mais um veiculo de carga? (s/n): ");
                if (resposta.equalsIgnoreCase("n")) {
                    System.out.println("Voltando ao menu inicial.");
                    return;
                }

            } catch (VeicExistException e) {
                System.out.println(e.getMessage());
                System.out.println("Voltando ao menu inicial.");
                return;
            }
        } while (true);
    }

    public void imprimirTodosPasseio() {
        System.out.println("\nVeiculos de Passeio Cadastrados:");
        for (Passeio p : bdVeiculos.getListaPasseio()) {
            if (p != null) {
                System.out.println("===== Veiculo de Passeio Cadastrado: =====");
                System.out.println("\nPlaca := " + p.getPlaca());
                System.out.println("Marca := " + p.getMarca());
                System.out.println("Modelo := " + p.getModelo());
                System.out.println("Cor := " + p.getCor());
                System.out.println("Velocidade Maxima (km/h) := " + p.getVelocMax());
                System.out.println("Velocidade Maxima (m/h) := " + p.calcVel(p.getVelocMax()));
                System.out.println("Quantidade de Rodas: " + p.getQtdRodas());
                System.out.println("Qtd Passageiros := " + p.getQtdPassageiros());
                System.out.println("Qtd Pistoes Motor := " + p.getMotor().getQtdPist());
                System.out.println("Potencia do Motor := " + p.getMotor().getPotencia());
                System.out.println("Valor calculado Interface := " + p.calcular());
            }
        }
    }

    public void imprimirTodosCarga() {
        System.out.println("Veiculos de Carga Cadastrados:");
        for (Carga c : bdVeiculos.getListaCarga()) {
            if (c != null) {
                System.out.println("\nPlaca := " + c.getPlaca());
                System.out.println("Marca := " + c.getMarca());
                System.out.println("Modelo := " + c.getModelo());
                System.out.println("Cor := " + c.getCor());
                System.out.println("Velocidade Maxima (km/h) := " + c.getVelocMax());
                System.out.println("Velocidade Maxima (cm/h) := " + c.calcVel(c.getVelocMax()));
                System.out.println("Quantidade de Rodas: " + c.getQtdRodas());
                System.out.println("Carga maixma := " + c.getCargaMax());
                System.out.println("Tara := " + c.getTara());
                System.out.println("Qtd Pistoes Motor := " + c.getMotor().getQtdPist());
                System.out.println("Potencia do Motor := " + c.getMotor().getPotencia());
                System.out.println("Valor calculado Interface := " + c.calcular());
            }
        }
    }

    public void imprimirPasseioPorPlaca() {
        String placa = l.entDados("Digite a placa do veiculo de passeio para imprimir:");
        for (Passeio p : bdVeiculos.getListaPasseio()) {
            if (p != null && p.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Veiculo de Passeio Encontrado:");
                System.out.println("\nPlaca := " + p.getPlaca());
                System.out.println("Marca := " + p.getMarca());
                System.out.println("Modelo := " + p.getModelo());
                System.out.println("Cor := " + p.getCor());
                System.out.println("Velocidade Maxima (km/h) := " + p.getVelocMax());
                System.out.println("Velocidade Maxima (m/h) := " + p.calcVel(p.getVelocMax()));
                System.out.println("Quantidade de Rodas: " + p.getQtdRodas());
                System.out.println("Qtd Passageiros := " + p.getQtdPassageiros());
                System.out.println("Qtd Pistoes Motor := " + p.getMotor().getQtdPist());
                System.out.println("Potencia do Motor := " + p.getMotor().getPotencia());
                System.out.println("Valor calculado Interface := " + p.calcular());
                return;
            }
        }
        System.out.println("Veiculo de passeio com placa " + placa + " não encontrado.");
    }

    public void imprimirCargaPorPlaca() {
        String placa = l.entDados("Digite a placa do veiculo de carga para imprimir:");
        for (Carga c : bdVeiculos.getListaCarga()) {
            if (c != null && c.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Veiculo de Carga Encontrado:");

                System.out.println("\nPlaca := " + c.getPlaca());
                System.out.println("Marca := " + c.getMarca());
                System.out.println("Modelo := " + c.getModelo());
                System.out.println("Cor := " + c.getCor());
                System.out.println("Velocidade Maxima (km/h) := " + c.getVelocMax());
                System.out.println("Velocidade Maxima (cm/h) := " + c.calcVel(c.getVelocMax()));
                System.out.println("Quantidade de Rodas: " + c.getQtdRodas());
                System.out.println("Carga maixma := " + c.getCargaMax());
                System.out.println("Tara := " + c.getTara());
                System.out.println("Qtd Pistoes Motor := " + c.getMotor().getQtdPist());
                System.out.println("Potencia do Motor := " + c.getMotor().getPotencia());
                System.out.println("Valor calculado Interface := " + c.calcular());
                return;
            }
        }
        System.out.println("Veiculo de carga com placa " + placa + " não encontrado.");
    }

    public void excluirPasseioPorPlaca() {
        String placa = l.entDados("Digite a placa do veiculo de passeio para excluir:");
        List<Passeio> listaPasseio = bdVeiculos.getListaPasseio();

        for (Passeio p : listaPasseio) {
            if (p != null && p.getPlaca().equalsIgnoreCase(placa)) {
                listaPasseio.remove(p);
                System.out.println("Veiculo de passeio com placa " + placa + " foi excluido.");
                return;
            }
        }
        System.out.println("Veiculo de passeio com placa " + placa + " nao encontrado.");
    }

    public void excluirCargaPorPlaca() {
        String placa = l.entDados("Digite a placa do veiculo de carga para excluir:");
        List<Carga> listaCarga = bdVeiculos.getListaCarga();

        for (Carga c : listaCarga) {
            if (c != null && c.getPlaca().equalsIgnoreCase(placa)) {
                listaCarga.remove(c);
                System.out.println("Veiculo de carga com placa " + placa + " foi excluido.");
                return;
            }
        }
        System.out.println("Veiculo de carga com placa " + placa + " nao encontrado.");
    }
}
