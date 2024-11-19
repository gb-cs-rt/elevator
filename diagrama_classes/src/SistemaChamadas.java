import java.util.ArrayList;

public class SistemaChamadas {

    public ArrayList<Elevador> elevadores = new ArrayList<Elevador>();
    public ArrayList<AndarVIP> andaresVIP = new ArrayList<AndarVIP>();

    public SistemaChamadas(ArrayList<Elevador> elevadores, ArrayList<AndarVIP> andaresVIP) {
        this.elevadores = elevadores;
        this.andaresVIP = andaresVIP;
    }

    public void desativarChamadas() {
        ;
    }

    public String tirarFoto() {
        return "Foto tirada!";
    }

    public void iniciarLogDaChamada() {
        ;
    }

    public void salvarLogDaChamada() {
        ;
    }

    public void chamarElevador(int andar) {

        iniciarLogDaChamada();
        
        for (AndarVIP andarVIP : andaresVIP) {
            if (andarVIP.getAndar() == andar) {
                String foto = tirarFoto();
                BiometriaFacial biometriaFacial = new BiometriaFacial(foto);
                if (andarVIP.verificarUsuario(biometriaFacial)) {
                    elevadores.get(0).proximosAndares.add(andar);
                } else {
                    throw new IllegalArgumentException("Usuário não autorizado");
                }
            } else {
                elevadores.get(0).proximosAndares.add(andar);
            }
        }

        salvarLogDaChamada();
}

}



















