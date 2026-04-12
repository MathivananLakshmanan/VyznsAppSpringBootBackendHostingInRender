package com.example.vyzns.Service;

import com.example.vyzns.Config.JwtUtil;
import com.example.vyzns.Dto.ItemRequest;
import com.example.vyzns.Dto.ItemsResponse;
import com.example.vyzns.Entity.Items;
import com.example.vyzns.Entity.User;
import com.example.vyzns.Repository.IteamRepository;
import com.example.vyzns.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemService {

    private   final UserRepository repo;
    private   final IteamRepository  itemRepo;
    private  final JwtUtil jwt;


    public ItemService(UserRepository repo, IteamRepository itemRepo, JwtUtil jwt) {
        this.repo = repo;
        this.itemRepo = itemRepo;
        this.jwt = jwt;
    }



    //CreateItem
    public ItemsResponse createItems( ItemRequest req, String token ){

        String email = jwt.extractEmail( token.replace("Bearer ", ""));
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Items item = new Items();
        item.setTitle(req.getTitle());
        item.setDescription(req.getDescription());
        item.setImage_url(req.getImage_url());
        item.setCategory(req.getCategory());
        item.setCondition(req.getCondition());
        item.setPrice(req.getPrice());
        item.setOwner(user);

          Items save = itemRepo.save(item);
          return  mapToResponse(save);
    }

        //AllItem
    public List<ItemsResponse> getAllItems(){
          return  itemRepo.findAll()
                  .stream()
                  .map(this::mapToResponse)
                  .collect(Collectors.toList());
    }

     //getItemByUser
    public List<ItemsResponse> getMyItemsByUser(String token){

        String email = jwt.extractEmail( token.replace("Bearer ", ""));
        User user =  repo.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not Found"));

              return  itemRepo.findByOwner(user)
                      .stream()
                      .map(this::mapToResponse)
                      .collect(Collectors.toList());

    }

    public  void  DeleteItems(Long id){

        Items items = itemRepo.findById(id).orElseThrow(() ->
                new RuntimeException( "Item not found  Iteam was Not Deleted") );
        itemRepo.delete(items);
    }

             //SearchItemKeywords
    public  List<ItemsResponse> serachByItem (String keywords){
          return itemRepo.findByTitleContainingIgnoreCase(keywords)
                  .stream()
                  .map(this::mapToResponse)
                  .collect(Collectors.toList());
    }

    public Items updateItem(Long id, ItemRequest request) {

        Items item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Optional.ofNullable(request.getTitle())
                .ifPresent(item::setTitle);

        Optional.ofNullable(request.getDescription())
                .ifPresent(item::setDescription);

        Optional.ofNullable(request.getImage_url())
                .ifPresent(item::setImage_url);

        Optional.ofNullable(request.getCategory())
                .ifPresent(item::setCategory);

        Optional.ofNullable(request.getPrice())
                .ifPresent(item::setPrice);

        Optional.ofNullable(request.getCondition())
                .ifPresent(item::setCondition);

        return itemRepo.save(item);
    }




    private ItemsResponse mapToResponse(Items item) {

        ItemsResponse res = new ItemsResponse();

        res.setId(item.getId());
        res.setTitle(item.getTitle());
        res.setDescription(item.getDescription());
        res.setImage_url(item.getImage_url());
        res.setCategory(item.getCategory());
        res.setCondition(String.valueOf(item.getCondition()));
        res.setStatus(String.valueOf(item.getStatus()));
        res.setPrice(item.getPrice());
        res.setOwner(item.getOwner().getId());

        return res;

    }


}
