public class AlarmeIncendio {

    public SistemaControle sistemaControle;

    public AlarmeIncendio(SistemaControle sistemaControle) {
        this.sistemaControle = sistemaControle;
    }

    public void dispararAlarme() {
        System.out.println("Alarme de incêndio disparado!");
        sistemaControle.ativarProcedimentoIncendio();
    }

}
