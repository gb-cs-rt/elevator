public class UsuarioComum {
    public String nome;
    public String email;
    public BiometriaFacial biometriaFacial;
    public Boolean admin;

    public UsuarioComum(String nome, String email, BiometriaFacial biometriaFacial) {
        this.nome = nome;
        this.email = email;
        this.biometriaFacial = biometriaFacial;
        this.admin = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BiometriaFacial getBiometriaFacial() {
        return biometriaFacial;
    }

    public void setBiometriaFacial(BiometriaFacial biometriaFacial) {
        this.biometriaFacial = biometriaFacial;
    }

    public void autenticarBiometria(BiometriaFacial biometriaFacial) {
        if (this.biometriaFacial.equals(biometriaFacial)) {
            System.out.println("Biometria autenticada com sucesso!");
        } else {
            System.out.println("Biometria não autenticada!");
        }
    }

    public Boolean isAdmin() {
        return admin;
    }

}
