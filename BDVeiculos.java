import java.util.ArrayList;
import java.util.List;

public class BDVeiculos {

    private List<Passeio> listaPasseio;
    private List<Carga> listaCarga;

    public BDVeiculos() {
        this.listaPasseio = new ArrayList<>();
        this.listaCarga = new ArrayList<>();
    }

    public List<Passeio> getListaPasseio() {
        return listaPasseio;
    }

    public List<Carga> getListaCarga() {
        return listaCarga;
    }

    public void setListaPasseio(List<Passeio> listaPasseio) {
        // if (listaPasseio.size() > 5) {
        //     throw new IllegalArgumentException("Lista de Passeio nao pode ter mais que cinco elementos.");
        // }
        this.listaPasseio = listaPasseio;
    }

    public void setListaCarga(List<Carga> listaCarga) {
        // if (listaCarga.size() > 5) {
        //     throw new IllegalArgumentException("Lista de Carga nao pode ter mais que cinco elementos.");
        // }
        this.listaCarga = listaCarga;
    }

    public boolean placaExiste(String placa, String tipo) {
        if ("passeio".equalsIgnoreCase(tipo)) {
            for (Passeio p : listaPasseio) {
                if (p != null && p.getPlaca().equalsIgnoreCase(placa)) {
                    return true;
                }
            }
        } else if ("carga".equalsIgnoreCase(tipo)) {
            for (Carga c : listaCarga) {
                if (c != null && c.getPlaca().equalsIgnoreCase(placa)) {
                    return true;
                }
            }
        }
        return false;
    }
}
