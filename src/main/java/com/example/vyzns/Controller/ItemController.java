package com.example.vyzns.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vyzns.Dto.ItemRequest;
import com.example.vyzns.Dto.ItemsResponse;
import com.example.vyzns.Entity.Items;
import com.example.vyzns.Service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {


      private final ItemService service;


    public ItemController(ItemService service) {
        this.service = service;
    }

       @PostMapping
        public ItemsResponse create(@RequestHeader("Authorization")
                                           String token,
                                    @RequestBody ItemRequest rer){

                return service.createItems(rer,token);
       }

       @GetMapping
       public  List<ItemsResponse> getAllItems(){

             return  service.getAllItems();

       }

       @GetMapping("/me")
      public  List<ItemsResponse> muItem (@RequestHeader("Authorization")
                                                 String token){

            return service.getMyItemsByUser(token);
       }

       @GetMapping("/search")
    public  List<ItemsResponse> SearchAllItem(@RequestParam
                                               String  keywords){
          return  service.serachByItem(keywords);
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<String> Deleteitem( @PathVariable Long id){

        service.DeleteItems(id);

        return ResponseEntity.ok("Item was Deleted");
    }

   @PatchMapping("/{id}")
    public  ResponseEntity<Items> UpdateItem (@PathVariable Long id ,
                                              @RequestBody ItemRequest request ){
       Items updateItems = service.updateItem(id,request);
        return  ResponseEntity.ok(updateItems);
   }


}
