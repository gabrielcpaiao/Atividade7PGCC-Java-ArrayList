public final class Carga extends Veiculo implements Calcular{
    private int cargaMax;
    private int tara;

    public Carga() {
        this.cargaMax = 0;
        this.tara = 0;
    }

    public int getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 100000;
    }

    @Override
    public int calcular() {
        int somaValores = 0;
        somaValores += getMotor().getQtdPist();
        somaValores += getMotor().getPotencia();
        somaValores += getVelocMax();
        somaValores += calcVel(getVelocMax());
        somaValores += getQtdRodas();
        somaValores += getCargaMax();
        somaValores += getTara();
        
        return somaValores;
    }
}
