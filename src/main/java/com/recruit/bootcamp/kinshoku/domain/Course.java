package com.recruit.bootcamp.kinshoku.domain;

import com.recruit.bootcamp.kinshoku.domain.common.Base;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by heyueheng on 5/9/16.
 */
@Data
@Document
public class Course extends Base {

    @NotNull
    private String storeId;

    @NotNull
    private Map<String, Float> priceMap;

    @NotNull
    private List<String> ingredients;


    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Map<String, Float> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Float> priceMap) {
        this.priceMap = priceMap;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
