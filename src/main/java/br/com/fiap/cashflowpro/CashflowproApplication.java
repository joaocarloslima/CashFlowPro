package br.com.fiap.cashflowpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class CashflowproApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashflowproApplication.class, args);
	}

	@RequestMapping
	@ResponseBody
	public String home(){
		return "Cash Flow Pro!";
	}

}
