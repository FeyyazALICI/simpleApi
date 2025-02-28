package com.simple_api.backend.components.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class CatDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @NotBlank(message = "Name is imperative!")
    private String name;

    @JsonProperty("weight")
    @NotBlank(message = "Weight is imperative!")
    private String weight;

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getWeight(){
        return this.weight;
    }

    public void setId(String id){
        if(id == null || id.trim().equals("")){
            this.id = null;
        }else{
            this.id = id;
        }
    }
    public void setName(String name){
        if(name == null || name.trim().equals("")){
            this.name = null;
        }else{
            this.name = name;
        }
    }
    public void setWeight(String weight){
        if(weight == null || weight.trim().equals("")){
            this.weight = null;
        }else{
            this.weight = weight;
        }
    }
    
    public CatDTO(
        String id,
        String name,
        String weight
    ){
        this.id     = id;
        this.name   = name;
        this.weight = weight;
    }
}
