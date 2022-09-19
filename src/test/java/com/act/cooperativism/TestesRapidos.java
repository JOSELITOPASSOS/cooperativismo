/**
 * 
 */
package com.act.cooperativism;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Joselito
 * Usada para testar novas implementações especificas de forma rapida.
 */
public class TestesRapidos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		verificarDuration();

		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		LocalDateTime dt1= LocalDateTime.parse("17-09-2022 09:00:00", f);
		LocalDateTime dt2= LocalDateTime.parse("17-09-2022 12:59:59", f);
		
		long diferencaSeg = Duration.between(dt1, dt2).getSeconds();
		long diferencaMin = Duration.between(dt1, dt2).toMinutes();
		long diferencaHora = Duration.between(dt1, dt2).toHours();
		
		System.out.println(dt1);
		
		System.out.println(diferencaSeg);
		System.out.println(diferencaMin);
		System.out.println(diferencaHora);

	}

	private static void verificarDuration() {
		Duration duration = Duration.ofHours(16);
		System.out.println(duration);
		
		Duration iso8601 = Duration.parse("PT3H");
		System.out.println(iso8601);
		
		System.out.println( iso8601.toHours() );
		System.out.println( iso8601.toMinutes() );

		Duration tempoVotacao = Duration.parse("PT1M");
		System.out.println( tempoVotacao.toMinutes() );
	}

}
