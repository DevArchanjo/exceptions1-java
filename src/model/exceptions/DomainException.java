package model.exceptions;

public class DomainException extends RuntimeException {//quando uma classe é exceção acrescentamos o sufixo Exception nela
	private static final long serialVersionUID = 1L;
	
	public DomainException(String msg) {
		super(msg);
	}
}

//se mudarmos a herança da classe DomainException de Exception para RuntimeException não seremos mais obrigados a tratar as exceções no código podendo retirar a propagação de exceção(Throws DomainException) e o bloco catch porém se houver uma exceção ela irá quebrar o programa. Por isso é indicado mantermos o bloco catch para capturar exceções esperadas e podemos criar um novo bloco catch para capturar exceções geradas em tempo de execução como digitar um número quando o Scanner espera uma letra.
