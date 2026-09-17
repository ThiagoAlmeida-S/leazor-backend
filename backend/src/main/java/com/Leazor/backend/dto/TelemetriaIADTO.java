package com.Leazor.backend.dto;

public class TelemetriaIADTO {
    private String classeDetectada;
    private Double confiabilidade;
    private Integer qtdObstaculos;
    private Integer qtdGramaAlta;
    private Integer qtdGramaBaixa;
    private String statusLamina;
    private String ultimoComando;

    // Getters e Setters
    public String getClasseDetectada() { return classeDetectada; }
    public void setClasseDetectada(String classeDetectada) { this.classeDetectada = classeDetectada; }

    public Double getConfiabilidade() { return confiabilidade; }
    public void setConfiabilidade(Double confiabilidade) { this.confiabilidade = confiabilidade; }

    public Integer getQtdObstaculos() { return qtdObstaculos; }
    public void setQtdObstaculos(Integer qtdObstaculos) { this.qtdObstaculos = qtdObstaculos; }

    public Integer getQtdGramaAlta() { return qtdGramaAlta; }
    public void setQtdGramaAlta(Integer qtdGramaAlta) { this.qtdGramaAlta = qtdGramaAlta; }

    public Integer getQtdGramaBaixa() { return qtdGramaBaixa; }
    public void setQtdGramaBaixa(Integer qtdGramaBaixa) { this.qtdGramaBaixa = qtdGramaBaixa; }

    public String getStatusLamina() { return statusLamina; }
    public void setStatusLamina(String statusLamina) { this.statusLamina = statusLamina; }

    public String getUltimoComando() { return ultimoComando; }
    public void setUltimoComando(String ultimoComando) { this.ultimoComando = ultimoComando; }
}
