public final class Passeio extends Veiculo implements Calcular {
    private int qtdPassageiros;

    public Passeio() {
        this.qtdPassageiros = 0;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    @Override
    public int calcular() {
        int somaCaracteres = 0;
        somaCaracteres += getPlaca().length();
        somaCaracteres += getMarca().length();
        somaCaracteres += getModelo().length();
        somaCaracteres += getCor().length();
        return somaCaracteres;
    }
}
