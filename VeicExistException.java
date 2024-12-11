public class VeicExistException extends Exception {
    public VeicExistException(String placa) {
        super("Já existe um veículo cadastrado com a placa: " + placa);
    }
}
