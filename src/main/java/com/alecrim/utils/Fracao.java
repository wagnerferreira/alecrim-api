package com.alecrim.utils;

public class Fracao {

    private int numerador;
    private int denominador;

    public Fracao(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("Denominador não pode ser zero");
        } else {
            this.numerador = numerador;
            this.denominador = denominador;
            
            if (this.denominador < 0) {
                this.denominador *= -1;
                this.numerador *= -1;
            }
        }
    }

    public Fracao(int numerador) { 
        this(numerador, 1);
    }

    public Fracao() { 
        this(0);
    }

    public int getNumerador() {
        return this.numerador;
    }

    public int getDenominador() {
        return this.denominador;
    }

    public String toString() {
        return this.numerador + "/" + this.denominador;
    }

    public Fracao soma(Fracao otherFracao) {
        Fracao resultado = new Fracao();
        resultado.denominador = this.denominador * otherFracao.denominador;
        this.numerador = (resultado.denominador / this.denominador) * this.numerador;
        otherFracao.numerador = (resultado.denominador / otherFracao.denominador) * otherFracao.numerador;
        resultado.numerador = this.numerador + otherFracao.numerador;
        resultado.simplicarFracao();
        resultado.zeroResult();
        return resultado;
    }

    public Fracao subtracao(Fracao otherFracao) {
        Fracao resultado = new Fracao();
        resultado.denominador = this.denominador * otherFracao.denominador;
        this.numerador = (resultado.denominador / this.denominador) * this.numerador;
        otherFracao.numerador = (resultado.denominador / otherFracao.denominador) * otherFracao.numerador;
        resultado.numerador = this.numerador - otherFracao.numerador;
        resultado.simplicarFracao();
        resultado.zeroResult();
        return resultado;
    }

    public Fracao multiplicao(Fracao otherFracao) {
        Fracao resultado = new Fracao();
        if (this.numerador != 0) {
            resultado.numerador = this.numerador * otherFracao.numerador;
            resultado.denominador = this.denominador * otherFracao.denominador;
            resultado.simplicarFracao();
            resultado.zeroResult();
            return resultado;
        } else {
            resultado.numerador = 0;
            resultado.denominador = 1;
            return resultado;
        }
    }

    public Fracao divisao(Fracao otherFracao) {
        Fracao resultado = new Fracao();
        if (this.numerador != 0) {
            resultado.numerador = this.numerador * otherFracao.denominador;
            resultado.denominador = this.denominador * otherFracao.numerador;
            resultado.simplicarFracao();
            resultado.zeroResult();
            return resultado;
        } else {
            resultado.numerador = 0;
            resultado.denominador = 1;
            return resultado;
        }
    }

    private void simplicarFracao() {
        int mmc = mmc(this.numerador, this.denominador);
        if (mmc != 0) {
            this.numerador /= mmc;
            this.denominador /= mmc;

            if (this.denominador < 0) {
                this.denominador *= -1;
                this.numerador *= -1;
            }
        }
    }

    private static int mmc(int numerador, int denominador) {
        while (numerador != 0 && denominador != 0) {
            int resto = numerador % denominador;
            numerador = denominador;
            denominador = resto;
        }
        return numerador;
    }

    private void zeroResult() {
        if (this.numerador == 0) {
            this.denominador = 1;
        }
    }

}