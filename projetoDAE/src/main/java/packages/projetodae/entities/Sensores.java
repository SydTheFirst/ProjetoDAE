package packages.projetodae.entities;

import jakarta.persistence.Id;

public class Sensores {
    @Id
    private int id;
    private float temperatura;
    private int humidade;
    private boolean embalagemAberta;
    private String localizacao;
    private float pressao;

    // Construtor
    public Sensores(float temperatura, int humidade, boolean embalagemAberta, String localizacao, float pressao) {
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.embalagemAberta = embalagemAberta;
        this.localizacao = localizacao;
        this.pressao = pressao;
    }

    // Getters
    public float getTemperatura() {
        return temperatura;
    }

    public int getHumidade() {
        return humidade;
    }

    public boolean isEmbalagemAberta() {
        return embalagemAberta;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public float getPressao() {
        return pressao;
    }

    // Setters
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setHumidade(int humidade) {
        this.humidade = humidade;
    }

    public void setEmbalagemAberta(boolean embalagemAberta) {
        this.embalagemAberta = embalagemAberta;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setPressao(float pressao) {
        this.pressao = pressao;
    }
}
