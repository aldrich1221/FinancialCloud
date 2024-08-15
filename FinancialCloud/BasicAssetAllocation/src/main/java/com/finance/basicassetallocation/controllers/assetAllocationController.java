package com.finance.basicassetallocation.controllers;

import com.finance.basicassetallocation.models.AllocationRequest;
import com.finance.basicassetallocation.models.User;
import com.finance.basicassetallocation.models.response.ApiResponse;
import com.finance.basicassetallocation.services.AssetAllocationService;
import com.finance.basicassetallocation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/allocation")
public class assetAllocationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AssetAllocationService assetAllocationService;

    @GetMapping("/test")
    public String test() {
        try {
            return "Welcome to test";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        List<User> users=userService.findAllUsers();
//        System.out.println(users);
//        return  users;
//    }
//
    @PostMapping(path="/basic",consumes = "application/json",produces = "application/json")
    public ApiResponse<?> basicMethod(@RequestBody AllocationRequest allocationRequest) throws IOException {
        String[] symbols = allocationRequest.getSymbols();
        HashMap<String,Double> ans=new HashMap<>();
        System.out.println(allocationRequest.getSymbols()[0]+" "+symbols[0].equals("SP500"));
        try {
            if(symbols[0].equals("SP500") && symbols.length == 1){

                    ans =assetAllocationService.simpleAllocationSP500();
                    System.out.println(ans);

            }else{
                throw new Exception("Invalid symbols");
            }

            ApiResponse<HashMap<String,Double>> response = new ApiResponse<>(200, "Success", ans);
            return response;
        }
        catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Exception> response = new ApiResponse<>(300, "Failure", e);
            return response;
        }
    }


}
