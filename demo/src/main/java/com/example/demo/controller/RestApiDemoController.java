import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/coffees")
class RestApiDemoController{
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController(){
        coffees.addAll(List.of( //addAll 값들을 전부 추가 List.of를 통하여 새로운 값들을 생성
            new Coffee("Cafe Cereza"),
            new Coffee("Cafe Ganaodr"),
            new Coffee("Cafe Tres Pontas")

        ));
    }
    
    
    // GetMapping으로 개선
    @GetMapping
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    //Optional 값이 있거나 없을 때 사용 가능
    // @PathVariable를 이용하여 /{id}의 값을 가져올 수 있음
    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        for(Coffee c : coffees){
            if(c.getId().equals){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }
    @PutMapping("/{id}") //Put은 crud에서 update 라고 생각하면 될듯
    Coffee putCoffee (@PathVariable String id , @RequestBody Coffee coffee){
        int coffeeIndex = -1;
        for(Coffee c: coffee){
            if(c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);   //c  > 인덱스 위치 찾음
                coffees.set(coffeeIndex , coffee);  //값 대체 (update)
            }
        }

        //없으면 post 있으면 새로운 coffee 반환
        //PUT은 응답시 상태코드 사용 필수!!
        //DELETE 와 POST는 응답시 상태코드 사용 권장
        return (coffeeIndex == -1) ? 
            new ResponseEntity<>(postCoffee(coffee),HttpStatus.CREATED):    //200(OK)
            new ResponseEntity<>(coffee, HttpStatus.OK);    //201(CREATED)


        
    }

    @DeleeteMapping("/{id}")
    void deleteCoffee(@PathVariable String id ){
        coffees.removeIf(c-> c.getId().equals(id));     //값이 true면 요소 삭제
    }

    
}
