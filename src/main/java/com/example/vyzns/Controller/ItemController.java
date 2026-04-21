package com.example.vyzns.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.vyzns.Service.B2StorageService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.vyzns.Dto.ItemRequest;
import com.example.vyzns.Dto.ItemsResponse;
import com.example.vyzns.Entity.Items;
import com.example.vyzns.Service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {


      private final ItemService service;
      private final B2StorageService b2StorageService;

    public ItemController(ItemService service, B2StorageService b2StorageService) {
        this.service = service;
        this.b2StorageService = b2StorageService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = b2StorageService.uploadFile(file);
        return ResponseEntity.ok(url);
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
