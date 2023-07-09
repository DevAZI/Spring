package com.example.demo;




@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RestController
	@RequestMapping("/greeting")
	class GreetingController{
		@Value("${greeting-name : Mirage}")
		private String name;

		@GetMapping
		String getGreeting(){
			return name;
		}
	}


}




