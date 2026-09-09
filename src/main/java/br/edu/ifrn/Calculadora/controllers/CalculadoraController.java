package br.edu.ifrn.Calculadora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/somar/{numero1}/{numero2}")
    public double somar(@PathVariable double numero1, @PathVariable double numero2) {
        return numero1 + numero2;
    }

    @GetMapping("/subtrair")
    public double subtrair(@RequestParam double numero1, @RequestParam double numero2) {
        return numero1 - numero2;
    }

    @GetMapping("/calcular/{operacao}")
    public String calcular(@PathVariable String operacao,
                            @RequestParam double numero1,
                            @RequestParam double numero2,
                            @RequestParam(defaultValue = "2") int casasDecimais) {

        String nomeOperacao;
        double resultado;

        switch (operacao) {
            case "somar":
                nomeOperacao = "soma";
                resultado = numero1 + numero2;
                break;
            case "subtrair":
                nomeOperacao = "subtração";
                resultado = numero1 - numero2;
                break;
            case "multiplicar":
                nomeOperacao = "multiplicação";
                resultado = numero1 * numero2;
                break;
            case "dividir":
                if (numero2 == 0) {
                    return "Erro: não é possível dividir por zero.";
                }
                nomeOperacao = "divisão";
                resultado = numero1 / numero2;
                break;
            default:
                return "Erro: operação inválida. Utilize somar, subtrair, multiplicar ou dividir.";
        }

        String resultadoFormatado = String.format("%." + casasDecimais + "f", resultado);

        return "Operação: " + nomeOperacao + "\n"
                + "Número 1: " + numero1 + "\n"
                + "Número 2: " + numero2 + "\n"
                + "Resultado: " + resultadoFormatado;
    }

    @GetMapping("/par-ou-impar/{numero}")
    public String parOuImpar(@PathVariable int numero) {
        return numero % 2 == 0 ? "PAR" : "ÍMPAR";
    }

    @GetMapping("/analisar/{numero}")
    public String analisar(@PathVariable int numero) {
        String parOuImpar = numero % 2 == 0 ? "PAR" : "ÍMPAR";

        String sinal;
        if (numero > 0) {
            sinal = "POSITIVO";
        } else if (numero < 0) {
            sinal = "NEGATIVO";
        } else {
            sinal = "ZERO";
        }

        return "Número: " + numero + "\n"
                + "Par ou ímpar: " + parOuImpar + "\n"
                + "Positivo, negativo ou zero: " + sinal + "\n"
                + "Dobro: " + (numero * 2) + "\n"
                + "Metade: " + (numero / 2.0) + "\n"
                + "Quadrado: " + (numero * numero);
    }

    @GetMapping("/media")
    public String media(@RequestParam double nota1,
                         @RequestParam double nota2,
                         @RequestParam double nota3) {

        double media = (nota1 + nota2 + nota3) / 3;

        String situacao;
        if (media >= 7) {
            situacao = "APROVADO";
        } else if (media >= 4) {
            situacao = "RECUPERAÇÃO";
        } else {
            situacao = "REPROVADO";
        }

        return "Média: " + media + "\n"
                + "Situação: " + situacao;
    }
}
