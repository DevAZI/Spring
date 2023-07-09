package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Entity;    //dependancy 추가 해야함
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
interface CoffeeRepository extends CrudRepository<Coffee, String>{
    private final CoffeeRepository coffeeRepository;
	public RestApiDemoController(CoffeeRepository, coffeeRepository){}
}



@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
    private final CoffeeRepository coffeeRepository;
	//private List<Coffee> coffees = new ArrayList<>();    
	// public RestApiDemoController() {
	// 	coffees.addAll(List.of(
	// 			new Coffee("Café Cereza"),
	// 			new Coffee("Café Ganador"),
	// 			new Coffee("Café Lareño"),
	// 			new Coffee("Café Três Pontas")
	// 	));
	// }
    public RestApiDemoController(CoffeeRepository coffeeRepository){        
        this.coffeeRepository = coffeeRepository;
        this.coffeeRepository.saveAll(List.of(
            	new Coffee("Café Cereza"),
				new Coffee("Café Ganador"),
				new Coffee("Café Lareño"),
				new Coffee("Café Três Pontas")
        ));
    }
    
    
	@GetMapping
	Iterable<Coffee> getCoffees() {
		return coffeeRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		return coffeeRepository.findById(id);
		// for (Coffee c: coffees) {
		// 	if (c.getId().equals(id)) {
		// 		return Optional.of(c);
		// 	}
		// }
		// return Optional.empty();

	}

	@PostMapping
	Coffee postCoffee(@RequestBody Coffee coffee) {
		return coffeeRepository.save(coffee);
		// coffees.add(coffee);
		// return coffee;
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffee(@PathVariable String id,
									 @RequestBody Coffee coffee) {
		return (!coffeeRepository.existsById(id))
			? new ResponseEntity<>(coffeeRepository.save(coffee),
				HttpStatus.CREATED)
			: new ResponseEntity<> (coffeeRepository.save(coffee),
				HttpStatus.OK);
			
		// int coffeeIndex = -1;

		// for (Coffee c: coffees) {
		// 	if (c.getId().equals(id)) {
		// 		coffeeIndex = coffees.indexOf(c);
		// 		coffees.set(coffeeIndex, coffee);
		// 	}
		// }

		// return (coffeeIndex == -1) ?
		// 		new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
		// 		new ResponseEntity<>(coffee, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
		// coffees.removeIf(c -> c.getId().equals(id));
		coffeeRepository.deleteById(id);
	}
}
@Entity
class Coffee {
    @Id
	private String id;
	private String name;

	public Coffee(String id, String name) {
		this.id = id;
		this.name = name;
	}
    public Coffee() {
    this.id = UUID.randomUUID().toString();
    }
	public Coffee(String name) {
        
		this(UUID.randomUUID().toString(), name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    public void setId(String id){
        this.id = id;
    }
}