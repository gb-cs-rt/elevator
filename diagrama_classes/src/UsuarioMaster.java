public class UsuarioMaster extends UsuarioAdmin {
    public String chaveMestra;

    public UsuarioMaster(String nome, String email, BiometriaFacial biometriaFacial, int senha) {
        super(nome, email, biometriaFacial, senha);
        this.chaveMestra = "123456";
    }

    public String getChaveMestra() {
        return chaveMestra;
    }

    public void getChaveMestra(String chaveMestra) {
        this.chaveMestra = chaveMestra;
    }

}
